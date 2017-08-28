package com.example.nicco.inspectionReviewManager.interfaces;

import android.app.FragmentManager;

/**
 * Created by Nicco on 2017-08-08.
 */

public interface ModelLoadListener {
    public void edit();
    public boolean exportHTML(FragmentManager fragmentManager);
    public boolean exportDoc(FragmentManager fragmentManager);
    public void delete();
}
