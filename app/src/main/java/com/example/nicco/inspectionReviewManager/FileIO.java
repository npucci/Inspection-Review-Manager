package com.example.nicco.inspectionReviewManager;
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

import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;

public class FileIO {
	public static final String OUTPUT_FOLDER = "Exported Inspection Reviews";
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

	public static boolean exportInpsectionReviewToDOC(final Context context,
                                                      final HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                                      String inspectionReviewName, String yearMonthDir) {
        // get directory of appropriate storage
        File storage = new File(getExternalPublicStorageDir(context), OUTPUT_FOLDER);
        storage = new File(storage, yearMonthDir);
        if(storage == null || (!storage.mkdirs() && !storage.exists())) {
            Log.v("PUCCI", "ERROR: directory in EXTERNAL PUBLIC STORAGE not created\n= " + storage.getPath());
            return false;
        }
        else Log.v("PUCCI", "SUCCESS: directory in EXTERNAL PUBLIC STORAGE created\n= " + storage.getPath());

        File outputFile = newFileFromTemplate(context, storage, inspectionReviewName);
        if (outputFile == null || !outputFile.exists()) {
            Log.v("PUCCI", "ERROR: The output file wasn't created\n= " + outputFile.getPath());
            return false;
        }

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(outputFile.getPath()));
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
            Log.v("PUCCI", "DOC Creation Success! outputFile = " + outputFile.getPath());
            out.close();

            Log.v("PUCCI", "SUCCESS: The output file was created/exists\n= " + outputFile.getPath());
            // open file using the Android OS default program
            openFile(context, outputFile);
        } catch (Exception e) {
            Log.v("PUCCI", "ERROR: " + e.getMessage());
            return false;
        }
        return outputFile != null;
    }

    private static File newFileFromTemplate(final Context context, final File storage, String fileName) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template);
        File dest = new File(storage.getPath() + "/" + fileName);
        return copyFile(inputStream, dest);
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
