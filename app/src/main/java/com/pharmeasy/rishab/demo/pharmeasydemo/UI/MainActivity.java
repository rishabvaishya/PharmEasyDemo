package com.pharmeasy.rishab.demo.pharmeasydemo.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pharmeasy.rishab.demo.pharmeasydemo.R;
import com.pharmeasy.rishab.demo.pharmeasydemo.adapters.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity
{

      /**
       * The {@link android.support.v4.view.PagerAdapter} that will provide
       * fragments for each of the sections. We use a
       * {@link FragmentPagerAdapter} derivative, which will keep every
       * loaded fragment in memory. If this becomes too memory intensive, it
       * may be best to switch to a
       * {@link android.support.v4.app.FragmentStatePagerAdapter}.
       */
      private SectionsPagerAdapter mSectionsPagerAdapter;

      /**
       * The {@link ViewPager} that will host the section contents.
       */
      private ViewPager mViewPager;

      @Override
      protected void onCreate( Bundle savedInstanceState )
      {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager() );

            // Set up the ViewPager with the sections adapter.
            mViewPager = ( ViewPager ) findViewById( R.id.container );
            mViewPager.setAdapter( mSectionsPagerAdapter );

      }

}
