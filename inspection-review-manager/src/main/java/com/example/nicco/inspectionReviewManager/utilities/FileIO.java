package com.example.nicco.inspectionReviewManager.utilities;
/* Author: Niccolo Pucci
 * Company: SEL Engineering Limited
 * Purpose: Aid inspector in writing the Inspection Report
 * Date: April 18th, 2017
 */

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FileIO {
    public static final String EXPORT_ROOT_DIRECTORY = "Inspection Review Manager";
	public static final String EXPORT_DOC_OUTPUT_FOLDER = EXPORT_ROOT_DIRECTORY + "/" + "Exported Inspection Reviews";
    public static final String EXPORT_DATABASE_OUTPUT_FOLDER = EXPORT_ROOT_DIRECTORY + "/" + "Exported Database Backups";
	public static final String CHECKBOX_CHECKED = "☑";
	public static final String CHECKBOX_BLANK = "☐";

    public static File getExternalPublicStorageDir(final Context context) {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        return storageDir;
    }

    private static void openFile(final Context context, final File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/msword");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean makeDir(File dir) {
        if(dir == null || (!dir.mkdirs() && !dir.exists())) {
            Log.v("PUCCI", "ERROR: directory not created\n= " + dir.getPath());
            return false;
        }
        else Log.v("PUCCI", "SUCCESS: directory created\n= " + dir.getPath());
        return true;
    }

    public static File getExportDocDir(Context context) {
        File exportDir = new File(getExternalPublicStorageDir(context), FileIO.EXPORT_DOC_OUTPUT_FOLDER);
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

//    public static boolean exportInpsectionReviewToHTML(final Context context,
//                                                      final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
//                                                      String inspectionReviewName) {
//        // 1. get export dir
//        File exportDir = getExportDocDir(context);
//        if(exportDir == null) return false;
//
//        // 2. copy correct template as new review file
////        boolean stamped = false;
////        if(hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED) != null &&
////                hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED).equals(Model.SpecialValue.YES)) stamped = true;
////        else stamped = false;
//        File outputFile = new File(exportDir.getPath(), inspectionReviewName);// newFileFromTemplate(context, exportDir, inspectionReviewName, stamped);
//        InputStream inputStream = context.getResources().openRawResource(R.raw.TemplateReportForm);
//        copyFile(inputStream, outputFile);
//
//        // 3. write to new review file
////        if(stamped) inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template);
////        else inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template_stamped);
//        ArrayList<String> lines = readHTMLReportTemplate(context);
//        Log.v("PUCCI", "# html lines = " + lines.size());
////        try {
////            POIFSFileSystem fs = new POIFSFileSystem(inputStream); //new FileInputStream(outputFile.getPath()));
////            HWPFDocument doc = new HWPFDocument(fs);
////            DatabaseWriter.UIComponentInputValue[] columnArr = hashMap.keySet().toArray(
////                    new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);
////            for (DatabaseWriter.UIComponentInputValue column : columnArr) {
////                String replacement = hashMap.get(column);
////                if(replacement.equals(Model.SpecialValue.YES.toString())) replacement = "checked";
////                else if(replacement.equals(Model.SpecialValue.NO.toString())) replacement = "unchecked";
////                String replacementTag = "<!-- " + column.getValue() + " -->";
////                replaceAllTextInDoc(doc, replacementTag, replacement);
////            }
////            FileOutputStream out = new FileOutputStream(outputFile);
////            doc.write(out);
////            //Log.v("PUCCI", "DOC Creation Success! outputFile = " + outputFile.getPath());
////            out.close();
////
////            //Log.v("PUCCI", "SUCCESS: The output file was created/exists\n= " + outputFile.getPath());
////            // open file using the Android OS default program
////            openFile(context, outputFile);
////        } catch (Exception e) {
////            Log.v("PUCCI", "ERROR: " + e.getMessage());
////            return false;
////        }
//
//        Log.v("PUCCI", "done");
//        if (outputFile == null || !outputFile.exists()) {
//            Log.v("PUCCI", "ERROR: The output file wasn't created\n= " + outputFile.getPath());
//            return false;
//        }
//        return true;
//
//    }

//    private static ArrayList<String> readHTMLReportTemplate(File reviewHTML) {
//        ArrayList<String> lines = new ArrayList<String>();
//        try {
//            FileReader reader = new FileReader(reviewHTML);
//            BufferedReader fin = new BufferedReader(reader);
//
//            for(String line = fin.readLine(); line != null; line = fin.readLine()) {
//                lines.add(line);
//            }
//        }
//        catch (Exception e) {
//            return null;
//        }
//
//        return lines;
//    }

	public static boolean exportInpsectionReviewToDOC(final Context context,
                                                      final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                                      String inspectionReviewName) {
        // 1. get export dir
        File exportDir = getExportDocDir(context);
        if(exportDir == null) return false;

        // 2. copy correct template as new review file
        boolean stamped = false;
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED) != null &&
                hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED).equals(Model.SpecialValue.YES)) stamped = true;
        else stamped = false;
        File outputFile = new File(exportDir.getPath(), inspectionReviewName);// newFileFromTemplate(context, exportDir, inspectionReviewName, stamped);

        // 3. write to new review file
        InputStream inputStream;
        if(stamped) inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template);
        else inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template_stamped);
        try {
            POIFSFileSystem fs = new POIFSFileSystem(inputStream); //new FileInputStream(outputFile.getPath()));
            HWPFDocument doc = new HWPFDocument(fs);
            DatabaseWriter.UIComponentInputValue[] columnArr = hashMap.keySet().toArray(
                    new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);
            for (DatabaseWriter.UIComponentInputValue column : columnArr) {
                String replacement = hashMap.get(column);
                if(replacement.equals(Model.SpecialValue.YES.toString())) replacement = CHECKBOX_CHECKED;
                else if(replacement.equals(Model.SpecialValue.NO.toString())) replacement = CHECKBOX_BLANK;
                String replacementTag = "<!-- " + column.getValue() + " -->";
                replaceAllTextInDoc(doc, replacementTag, replacement);
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            doc.write(out);
            //Log.v("PUCCI", "DOC Creation Success! outputFile = " + outputFile.getPath());
            out.close();

            //Log.v("PUCCI", "SUCCESS: The output file was created/exists\n= " + outputFile.getPath());
            // open file using the Android OS default program
            openFile(context, outputFile);
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
}
