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

//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.usermodel.CharacterRun;
//import org.apache.poi.hwpf.usermodel.Paragraph;
//import org.apache.poi.hwpf.usermodel.Range;
//import org.apache.poi.hwpf.usermodel.Section;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileIO {
	private static final String OUTPUT_FOLDER = "output/";
	private static final String TEMPLATE_FOLDER = "templates/";
    private static final String TEMPLATE_NAME = "";
	private static File outputFile = null;
	private static FileWriter writer = null;
	private static FileReader reader = null;
	private static BufferedReader fin = null;
	private static BufferedWriter fout = null;
	
	// HTML Report Template Insertion Tags
	public static final String PROJECT_INPUT = "<!-- Project Input -->";
	public static final String CITY_PROV_INPUT = "<!-- CityProv Input -->";
	public static final String PROJECT_NO_INPUT = "<!-- Project Number Input -->";
	public static final String DATE_INPUT = "<!-- Date Input -->";
	public static final String DEVELOPER_INPUT = "<!-- Developer Input -->";
	public static final String TIME_INPUT = "<!-- Time Input -->";
	public static final String WEATHER_INPUT = "<!-- Weather Input -->";
	public static final String CONTRACTOR_INPUT = "<!-- Contractor Input -->";
	public static final String TEMPERATURE_INPUT = "<!-- Temperature Input -->";
	public static final String OTHER_INPUT = "<!-- Other Input -->";
	// CONCRETE:
	public static final String REBAR_POSITION_INSTRUCTION_INPUT = "<!-- Rebar Position Instruction Input -->";
	public static final String REBAR_SIZE_SPACING_INSTRUCTION_INPUT = "<!-- Rebar Size Spacing Instruction Input -->";
	public static final String HOLD_DOWN_ANCHORAGE_INSTRUCTION_INPUT = "<!-- Hold Down Anchorage Instruction Input -->";
	public static final String FORMWORK_INSTRUCTION_INPUT = "<!-- Formwork Instruction Input -->";
	public static final String TRUSS_SPEC_INSTRUCTION_INPUT = "<!-- Truss Spec Instruction Input -->";
	public static final String LJOIST_INSTRUCTION_INPUT = "<!-- LJoist Instruction Input -->";
	public static final String BEARING_INSTRUCTION_INPUT = "<!-- Bearing Instruction Input -->";
	public static final String TOP_PLATES_INSTRUCTION_INPUT = "<!-- Top Plates Instruction Input -->";
	public static final String OPENINGS_INSTRUCTION_INPUT = "<!-- Openings Instruction Input -->";
	public static final String FASTENING_INSTRUCTION_INPUT = "<!-- Fastening Instruction Input -->";
	public static final String TALL_WALLS_INSTRUCTION_INPUT = "<!-- Tall Walls Instruction Input -->";
	public static final String BLOCKING_INSTRUCTION_INPUT = "<!-- Blocking Instruction Input -->";
	public static final String WALL_SHEATHING_INSTRUCTION_INPUT = "<!-- Wall Sheathing Instruction Input -->";
	public static final String WIND_GIRTS_INSTRUCTION_INPUT = "<!-- Wind Girts Instruction Input -->";
	public static final String OBSERVATIONS_INPUT = "<!-- Observations Input -->";
	public static final String COMMENTS_INPUT = "<!-- Comments Input -->";
	public static final String INSPECTOR_NAME_INPUT = "<!-- Inspector Name Input -->";
	
	// HTML Report Template Checkbox Tags
	// TYPE OF REVIEW
	public static final String FOOTINGS_CHECK = "T1";
	public static final String FOUNDATION_WALLS_CHECK = "T2";
	public static final String SHEATHING_CHECK = "T3";
	public static final String FRAMING_CHECK = "T4";
	public static final String OTHER_CHECK = "T5";
	// CONCRETE:
	public static final String REBAR_POSITION_REVIEWED = "A1";
	public static final String REBAR_POSITION_NOT_APPLICABLE = "A2";
	public static final String REBAR_SIZE_SPACING_REVIEWED = "B1";
	public static final String REBAR_SIZE_SPACING_NOT_APPLICABLE = "B2";
	public static final String HOLD_DOWN_ANCHORAGE_REVIEWED = "C1";
	public static final String HOLD_DOWN_ANCHORAGE_NOT_APPLICABLE = "C2";
	public static final String FORMWORK_REVIEWED = "D1";
	public static final String FORMWORK_NOT_APPLICABLE = "D2";
	// FRAMING:
	public static final String TRUSS_SPEC_REVIEWED = "E1";
	public static final String TRUSS_SPEC_NOT_APPLICABLE = "E2";
	public static final String LJOIST_REVIEWED = "F1";
	public static final String LJOIST_NOT_APPLICABLE = "F2";
	public static final String BEARING_REVIEWED = "G1";
	public static final String BEARING_NOT_APPLICABLE = "G2";
	public static final String TOP_PLATES_REVIEWED = "H1";
	public static final String TOP_PLATES_NOT_APPLICABLE = "H2";
	public static final String OPENINGS_REVIEWED = "I1";
	public static final String OPENINGS_NOT_APPLICABLE = "I2";
	public static final String FASTENING_REVIEWED = "J1";
	public static final String FASTENING_NOT_APPLICABLE = "J2";
	public static final String TALL_WALLS_REVIEWED = "K1";
	public static final String TALL_WALLS_NOT_APPLICABLE = "K2";
	public static final String BLOCKING_REVIEWED = "L1";
	public static final String BLOCKING_NOT_APPLICABLE = "L2";
	public static final String WALL_SHEATHING_REVIEWED = "M1";
	public static final String WALL_SHEATHING_NOT_APPLICABLE = "M2";
	public static final String WIND_GIRTS_REVIEWED = "N1";
	public static final String WIND_GIRTS_NOT_APPLICABLE = "N2";
	// REPORT GRADING
	public static final String REPORT_APPROVED = "P4";
	public static final String REPORT_NOT_APPROVED = "P2";
	public static final String REPORT_REINSPECTION_REQUIRED = "P3";
	
	// CHECKBOXES
	public static final String CHECKBOX_CHECKED = "☑";
	public static final String CHECKBOX_BLANK = "☐";
	
	public static String createFilePath(String project, String city, String province, String date, String time) {
		String filePath = "";
		
		String year = date.split("-")[0];
		String month = date.split("-")[1];
		int monthNumber = Integer.parseInt(month);
		String day = date.split("-")[2];
		
		String subParentDirectory = year;
		String subChildDirectory = month;
		String dir = OUTPUT_FOLDER + subParentDirectory + "/" + subChildDirectory + "/";
		
		String fileName = project + ", " + city + ", " + province + " - " + day + "/" + monthNumber + "/" + year + " - " + time;
		String extension = ".doc";
		
		filePath = dir + fileName + extension;
		return filePath;
	}
	
	private static void openReportFileInBrowser(String reportFilePath) {
//		File reportFile = new File(reportFilePath);
//		try {
//			Desktop.getDesktop().browse(reportFile.toURI());
//		} catch (IOException e) {
//			GeneralUtilities.showErrorDialogueWindow("Error", e.getMessage());
//		}
	}
	
	public static boolean openReportFileDoc(String reportFilePath) {
//		File reportFile = new File(reportFilePath);
//		try {
//			Desktop.getDesktop().browse(reportFile.toURI());
//		} catch (IOException e) {
//			GeneralUtilities.showErrorDialogueWindow("Error", "Error: Could not find the file: " + reportFile.getName());
//			return false;
//		}
		return true;
	}

    public static boolean exportInpsectionReviewToDOC(final Context context, String inspectionReviewName) {
		// get directory of appropriate storage
        File storage = getAppropriateStorageDir(context);
        //File templateSrc = new File(TEMPLATE_FOLDER + "/" + "sel_engineering_limited_inspection_report_templatete.doc");
        File outputFile = newFileFromTemplate(context, storage, inspectionReviewName);

//        try {
//            output.createNewFile();
//        } catch(Exception e) {
//            Log.v("PUCCI", "ERROR: The output file wasn't created\n= " + output.getPath());
//        }

        if(outputFile == null) Log.v("PUCCI", "ERROR: The output file wasn't created\n= null");
        else if(outputFile.exists()) {
            Log.v("PUCCI", "SUCCESS: The output file was created/exists\n= " + outputFile.getPath());
            // open file
//          openFile(context, output);
        }
        else Log.v("PUCCI", "ERROR: The output file wasn't created\n= " + outputFile.getPath());
        return outputFile != null;
	}

    private static File getAppropriateStorageDir(final Context context) {
        File storageDir = getExternalStorageDir();
        if(storageDir == null) {
            storageDir = getInternalCachedStorageDir(context);
        }
        return storageDir;
    }

    private static File getExternalStorageDir() {
        File storageDir = null;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            storageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS).getPath(), "InspectionReviews");
            String path = storageDir.getPath();
            if(!storageDir.mkdirs() && !storageDir.exists()) Log.v("PUCCI", "ERROR: \ndirectory in EXTERNAL STORAGE not created\n= " + path);
            else Log.v("PUCCI", "SUCCESS: directory in EXTERNAL STORAGE created/exists\n= " + path);
        }
        return storageDir;
    }

    private static File getInternalCachedStorageDir(final Context context) {
        File storageDir = new File(context.getCacheDir().getPath());
        String path = storageDir.getPath();
        if(!storageDir.mkdirs()) Log.v("PUCCI", "ERROR: directory in INTERNAL STORAGE not created\n= " + path);
        else Log.v("PUCCI", "SUCCESS: directory in INTERNAL CACHED STORAGE created\n= " + path);
        internalCachedStorageCleanUp();
        return storageDir;
    }

    private static File getInternalStorageDir(final Context context) {
        File storageDir = new File(context.getFilesDir().getPath());
        String path = storageDir.getPath();
        if(!storageDir.mkdirs()) Log.v("PUCCI", "ERROR: directory in EXTERNAL STORAGE not created");
        else Log.v("PUCCI", "SUCCESS: directory in INTERNAL CACHED STORAGE created\n= " + path);
        return storageDir;
    }

    private static void internalCachedStorageCleanUp() {

    }

    private void openFile(final Context context, final File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "text/*"); // "application/msword");
        context.startActivity(intent);
    }

    private static File newFileFromTemplate(final Context context, final File destination, String fileName) {
        File newFile = null;
        InputStream inputStream = context.getResources().openRawResource(R.raw.sel_engineering_limited_inspection_report_template);
        FileOutputStream outputStream = null;
        String newFilePath = destination.getPath() + "/" + fileName;
        try {
            outputStream = new FileOutputStream(newFilePath);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            newFile = new File(newFilePath);
        } catch (FileNotFoundException e) {
            Log.v("PUCCI", "ERROR: could not create the file " + fileName + ":\n" + e.getMessage());
        } catch (IOException e) {
            Log.v("PUCCI", "ERROR: could not create the file " + fileName + ":\n" + e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                Log.v("PUCCI", "ERROR: could not create the file " + fileName + ":\n" + e.getMessage());
            }
        }
        return newFile;
    }

	
//	public static boolean exportReportToDOC(Context context, HashMap<DatabaseWriter.DatabaseColumn, String> hashMap, String fileName, String year, String month) {
//		File templateSrc = new File(TEMPLATE_FOLDER + "/" + "sel_engineering_limited_inspection_report_templatete.doc");
//		File output = null;
//		if((output = copyTemplateFile(templateSrc, fileName, year, month)) == null) return false;
//		try {
//			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(output.getPath()));
//			HWPFDocument doc = new HWPFDocument(fs);
//			DatabaseWriter.DatabaseColumn[] columnArr = hashMap.keySet().toArray(new DatabaseWriter.DatabaseColumn[hashMap.keySet().size()]);
//			for(DatabaseWriter.DatabaseColumn column : columnArr) {
//				replaceAllTextInDoc(doc, column.getValue(), hashMap.get(column));
//			}
//			FileOutputStream out = new FileOutputStream(getAppropriateStorageDir(context, output.getPath()));
//			doc.write(out);
//			Log.v("PUCCI", "DOC Creation Success!");
//			out.close();
//		} catch (Exception e) {
//			Log.v("PUCCI", "ERROR: " + e.getMessage());
//			return false;
//		}
//
//		return openReportFileDoc(output.getPath());
//	}
//
//	private static void replaceAllTextInDoc(HWPFDocument doc, String tag, String replacement) {
//		Range r = doc.getRange();
//
//		for(int i = 0; i < r.numSections(); i++) {
//			replaceAllTextInSection(r.getSection(i), tag, replacement);
//		}
//	}
//
//	private static void replaceAllTextInSection(Section s, String tag, String replacement) {
//		for(int i = 0; i < s.numParagraphs(); i++) {
//			replaceAllTextInParagraph(s.getParagraph(i), tag, replacement);
//		}
//	}
//
//	private static void replaceAllTextInParagraph(Paragraph p, String tag, String replacement) {
//		for(int i = 0; i < p.numCharacterRuns(); i++) {
//			replaceAllTextInCharacterRun(p.getCharacterRun(i), tag, replacement);
//		}
//	}
//
//	private static void replaceAllTextInCharacterRun(CharacterRun r, String tag, String replacement) {
//		String text = r.text();
//		if(text.contains(tag)) {
//			r.replaceText(tag, replacement);
//		}
//	}
//
//	private static File copyTemplateFile(File templateSrc, String newFileName, String year, String month) {
//		String subParentDirectory = year;
//		String subChildDirectory = month;
//		File subDir = new File (OUTPUT_FOLDER + subParentDirectory + "/" + subChildDirectory);
//		File output = new File(subDir.getPath() + "/" + newFileName + ".doc");
//		try {
//			subDir.mkdirs();
//			//Files.copy(templateSrc.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		} catch (Exception e) {
//			//GeneralUtilities.showErrorDialogueWindow("Error", e.getMessage());
//			return null;
//		}
//		return output;
//	}
//
//	private static File getAppropriateStorageDir(Context context, String dir) {
//        File storageDir = getExternalStorageDir(dir);
//        if(storageDir == null) {
//            storageDir = getInternalCachedStorageDir(context, dir);
//        }
//
//        return storageDir;
//    }
//
//    private static File getExternalStorageDir(String dir) {
//        File storageDir = null;
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            storageDir = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOCUMENTS).getPath() + OUTPUT_FOLDER, dir);
//            String path = storageDir.getPath();
//            if(!storageDir.mkdirs()) Log.v("PUCCI", "ERROR: directory in EXTERNAL STORAGE not created");
//            else Log.v("PUCCI", "SUCCESS: directory in EXTERNAL STORAGE created = " + path);
//        }
//        return storageDir;
//    }
//
//    private static File getInternalCachedStorageDir(Context context, String dir) {
//        File storageDir = new File(context.getCacheDir().getPath() + OUTPUT_FOLDER + dir);
//        String path = storageDir.getPath();
//        if(!storageDir.mkdirs()) Log.v("PUCCI", "ERROR: directory in EXTERNAL STORAGE not created");
//        else Log.v("PUCCI", "SUCCESS: directory in INTERNAL CACHED STORAGE created = " + path);
//        internalCachedStorageCleanUp();
//        return storageDir;
//    }
//
//    private static File getInternalStorageDir(Context context) {
//        File storageDir = new File(context.getFilesDir().getPath() + TEMPLATE_FOLDER + TEMPLATE_NAME);
//        String path = storageDir.getPath();
//        if(!storageDir.mkdirs()) Log.v("PUCCI", "ERROR: directory in EXTERNAL STORAGE not created");
//        else Log.v("PUCCI", "SUCCESS: directory in INTERNAL CACHED STORAGE created = " + path);
//        // if contains more than max number of internally cached files allowed, delete some and make room for new
//        internalCachedStorageCleanUp();
//        return storageDir;
//    }
//
//    private static void internalCachedStorageCleanUp() {
//
//    }
}
