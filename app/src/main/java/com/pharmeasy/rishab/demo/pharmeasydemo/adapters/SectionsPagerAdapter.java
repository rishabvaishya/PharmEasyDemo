package com.pharmeasy.rishab.demo.pharmeasydemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pharmeasy.rishab.demo.pharmeasydemo.db.DatabaseHelper;
import com.pharmeasy.rishab.demo.pharmeasydemo.db.MainApplication;
import com.pharmeasy.rishab.demo.pharmeasydemo.fragments.DrugDetailFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
{

      public SectionsPagerAdapter( FragmentManager fm )
      {
            super( fm );
      }

      @Override
      public Fragment getItem( int position )
      {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return DrugDetailFragment.newInstance( position + 1 );
      }

      @Override
      public int getCount()
      {
            // Show 3 total pages.
            DatabaseHelper db = MainApplication.getDatabaseHelper();
            return db.getDrugCount();
      }

      @Override
      public CharSequence getPageTitle( int position )
      {
            return null;
      }
}