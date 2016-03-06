package com.pharmeasy.rishab.demo.pharmeasydemo.db;

import android.app.Application;

public class MainApplication extends Application
{

      /**
       * see NotePad tutorial for an example implementation of DataDbAdapter
       */
      private static DatabaseHelper mDbHelper;

      /**
       * Called when the application is starting, before any other
       * application objects have been created. Implementations
       * should be as quick as possible...
       */
      @Override
      public void onCreate()
      {
            // to ensure only one instance of databaseHandler
            mDbHelper = new DatabaseHelper( this );
            super.onCreate();
      }

      public static DatabaseHelper getDatabaseHelper()
      {
            return mDbHelper;
      }
}