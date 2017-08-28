package com.example.nicco.inspectionReviewManager.utilities;
/* Author: Niccolo Pucci
 * Company: SEL Engineering Limited
 * Purpose: Aid inspector in writing the Inspection Report
 * Date: April 18th, 2017
 */

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.nicco.inspectionReviewManager.R.raw.sel_engineering_limited_inspection_report_doc_template;

public class FileIO {
    public static final String EXPORT_ROOT_DIRECTORY = "Inspection Review Manager";
	public static final String EXPORT_OUTPUT_FOLDER = EXPORT_ROOT_DIRECTORY + "/" + "Exported Inspection Reviews";
    public static final String EXPORT_DATABASE_OUTPUT_FOLDER = EXPORT_ROOT_DIRECTORY + "/" + "Exported Database Backups";
	public static final String CHECKBOX_CHECKED = "☑";
	public static final String CHECKBOX_BLANK = "☐";

    public static File getExternalPublicStorageDir(final Context context) {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        return storageDir;
    }

    private static void openDocFile(final Context context, final File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/msword";
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            context.startActivity(intent);
        } catch(ActivityNotFoundException e) {
            Log.v("PUCCI", "ERROR: " + e.getMessage());
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    private static void openHTMLFile(final Context context, final File file) {
        Uri uri = Uri.parse("googlechrome://navigate?url=" + file.getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch(ActivityNotFoundException e) {
            Log.v("PUCCI", "ERROR: " + e.getMessage());
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    public static boolean makeDir(File dir) {
        if(dir == null || (!dir.mkdirs() && !dir.exists())) {
            Log.v("PUCCI", "ERROR: directory not created\n= " + dir.getPath());
            return false;
        }
        else Log.v("PUCCI", "SUCCESS: directory created\n= " + dir.getPath());
        return true;
    }

    public static File getExportDir(Context context) {
        File exportDir = new File(getExternalPublicStorageDir(context), FileIO.EXPORT_OUTPUT_FOLDER);
        if(!makeDir(exportDir)) return null;
        return exportDir;
    }

    public static File getExportDatabaseDir(Context context) {
        File exportDir = new File(getExternalPublicStorageDir(context), FileIO.EXPORT_DATABASE_OUTPUT_FOLDER);
        if(!makeDir(exportDir)) return null;
        return exportDir;
    }

    public static boolean exportDatabase(Context context, File database) {
        if(database == null) return false;

        // 1. get export dir
        File databaseBackup = new File(database.getPath());
        File destinationDir = getExportDatabaseDir(context);
        if(destinationDir == null) return false;

        // 2. copy database to export dir
        try (InputStream inputStream = new FileInputStream(databaseBackup)) {
            File destFile = new File(destinationDir.getPath(), databaseBackup.getName());
            FileIO.copyFile(inputStream, destFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean exportInspectionReviewToHTML(final Context context,
                                                       final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                                       String inspectionReviewName,
                                                       Model.ExportHTML asyncTask) {
        Log.v("PUCCI", "exporting html");
        asyncTask.doProgress(0);

        // 1. get export dir
        File exportDir = getExportDir(context);
        if(exportDir == null) return false;
        asyncTask.doProgress(0);

        // 2. copy correct template as new review file
        InputStream inputStream;
        boolean stamped = false;
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED) != null &&
                hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED).equals(Model.SpecialValue.YES)) stamped = true;
        if(stamped) inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_html_template_stamped);
        else inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_html_template);
        asyncTask.doProgress(50);

        File exportFile = copyFile(inputStream, new File(exportDir, inspectionReviewName + ".html"));
        if(!exportFile.exists()) return false;
        asyncTask.doProgress(75);

        // 3. write values to new review file
        boolean result = writeValuesToHTML(exportFile, hashMap);
        if(result) openHTMLFile(context, exportFile);
        asyncTask.doProgress(100);
        return result;
    }

    private static ArrayList<String> extractLinesFromFile(File file) {
        if (file == null || !file.exists() || !file.isFile()) return null;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader fin = new BufferedReader(reader);

            for(String line = fin.readLine(); line != null; line = fin.readLine()) {
                lines.add(line);
            }
        }
        catch (Exception e) {
            Log.v("PUCCI", "Error: " + e.getMessage());
            return null;
        }

        if(lines.size() == 0) return null;
        return lines;
    }

    private static boolean writeValuesToHTML(File htmlFile, final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap) {
        ArrayList<String> lines = extractLinesFromFile(htmlFile);
        try {
            FileWriter writer = new FileWriter(htmlFile);
            BufferedWriter fout = new BufferedWriter(writer);
            for (String s : lines) {
                if(s.contains("<!--") && s.contains("-->")) {
                    int start = s.indexOf("<!--");
                    int end = s.indexOf("-->") + 3;
                    String tag = s.substring(start, end);
                    String token = tag;
                    token = token.replace("<!-- ", "");
                    token = token.replace(" -->", "");
                    token = token.replace("<!--", "");
                    token = token.replace("-->", "");
                    String value = hashMap.get(DatabaseWriter.getUIComponentInputValue(token));
                    if(value != null) {
                        Log.v("PUCCI", "token = " + token);
                        Log.v("PUCCI", "value = " + value);
                        if(value.equals(Model.SpecialValue.YES.toString())) {
                            s = s.replace("unchecked", "checked");
                            s = s.replace(value, "");
                        } else if(value.equals(Model.SpecialValue.NO.toString())) s = s.replace(value, "");
                        else s = s.replace("<!-- " + token + " -->", value);
                    }
                }
                //Log.v("PUCCI", "s = " + s);
                fout.write(s);
                fout.write("\n");
            }
            fout.close();
            writer.close();
        } catch (IOException e) {
            Log.v("PUCCI", "Error: " + e.getMessage());
            return false;
        }
        Log.v("PUCCI", "finished");
        return true;
    }

    public static boolean exportInspectionReviewToDOC(final Context context,
                                                      final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                                      String inspectionReviewName,
                                                      Model.ExportDoc asyncTask) {

        asyncTask.doProgress(0);

        // 1. get export dir
        File exportDir = getExportDir(context);
        if(exportDir == null) return false;
        asyncTask.doProgress(10);

        // 2. copy correct template as new review file
        boolean stamped = false;
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED) != null &&
                hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED).equals(Model.SpecialValue.YES)) stamped = true;
        else stamped = false;
        File outputFile = new File(exportDir.getPath(), inspectionReviewName + ".doc");// newFileFromTemplate(context, exportDir, inspectionReviewName, stamped);
        asyncTask.doProgress(20);

        // 3. write to new review file
        InputStream inputStream;
        if(stamped) inputStream = context.getResources().openRawResource(sel_engineering_limited_inspection_report_doc_template);
        else inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_doc_template_stamped);
        asyncTask.doProgress(30);
        try {
            asyncTask.doProgress(40);
            POIFSFileSystem fs = new POIFSFileSystem(inputStream); //new FileInputStream(outputFile.getPath()));
            HWPFDocument doc = new HWPFDocument(fs);
            DatabaseWriter.UIComponentInputValue[] columnArr = hashMap.keySet().toArray(
                    new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);

            asyncTask.doProgress(50);
            int total = DatabaseWriter.UIComponentInputValue.values().length;
            int count = 0;
            for (DatabaseWriter.UIComponentInputValue column : columnArr) {
                String replacement = hashMap.get(column);
                if(replacement.equals(Model.SpecialValue.YES.toString())) replacement = CHECKBOX_CHECKED;
                else if(replacement.equals(Model.SpecialValue.NO.toString())) replacement = CHECKBOX_BLANK;
                String replacementTag = "<!-- " + column.getValue() + " -->";
                replaceAllTextInDoc(doc, replacementTag, replacement);
                count++;
                int progressPercentage = (int) (50 + (( ((float) count) / total) * 100 ) / 2);
                asyncTask.doProgress(progressPercentage);
                Log.v("TEST", progressPercentage + "%");
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            doc.write(out);
            //Log.v("PUCCI", "DOC Creation Success! outputFile = " + outputFile.getPath());
            out.close();
            //Log.v("PUCCI", "SUCCESS: The output file was created/exists\n= " + outputFile.getPath());
            // open file using the Android OS default program

            asyncTask.doProgress(100);
            openDocFile(context, outputFile);
        } catch (Exception e) {
            Log.v("PUCCI", "ERROR: " + e.getMessage());
            return false;
        }

        Log.v("PUCCI", "done");
        if (outputFile == null || !outputFile.exists()) {
            Log.v("PUCCI", "ERROR: The output file wasn't created\n= " + outputFile.getPath());
            return false;
        }
        return true;
    }

	private static void replaceAllTextInDoc(HWPFDocument doc, String replacementTag, String replacement) {
		Range r = doc.getRange();

		for(int i = 0; i < r.numSections(); i++) {
			replaceAllTextInSection(r.getSection(i), replacementTag, replacement);
		}
	}

	private static void replaceAllTextInSection(Section s, String tag, String replacement) {
		for(int i = 0; i < s.numParagraphs(); i++) {
			replaceAllTextInParagraph(s.getParagraph(i), tag, replacement);
		}
	}

	private static void replaceAllTextInParagraph(Paragraph p, String tag, String replacement) {
		for(int i = 0; i < p.numCharacterRuns(); i++) {
			replaceAllTextInCharacterRun(p.getCharacterRun(i), tag, replacement);
		}
	}

	private static void replaceAllTextInCharacterRun(CharacterRun r, String tag, String replacement) {
		String text = r.text();
		if(text.contains(tag)) {
			r.replaceText(tag, replacement);
		}
	}

    public static File copyFile(InputStream inputStream, File dest) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            Log.v("PUCCI", "ERROR: could not create the file " + dest.getName() + ":\n" + e.getMessage());
        } catch (IOException e) {
            Log.v("PUCCI", "ERROR: could not create the file " + dest.getName() + ":\n" + e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                Log.v("PUCCI", "ERROR: could not create the file " + dest.getName() + ":\n" + e.getMessage());
            }
        }
        if(dest.exists()) return dest;
        return null;
    }
}
