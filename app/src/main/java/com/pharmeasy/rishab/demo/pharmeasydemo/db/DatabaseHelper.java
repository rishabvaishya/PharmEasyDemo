package com.pharmeasy.rishab.demo.pharmeasydemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pharmeasy.rishab.demo.pharmeasydemo.dataObjects.Drug;

public class DatabaseHelper extends SQLiteOpenHelper
{
      static final String DBNAME       = "demoDB";
      static final int    DBVERSION    = 1;
      static final String DRUGTABLE    = "drug";
      static final String ID           = "id";
      static final String NAME         = "name";
      static final String MANUFACTURER = "manufacturer";
      static final String FORM         = "form";
      static final String AVAILABLE    = "available";
      static final String PRICE        = "price";

      public DatabaseHelper( Context context )
      {
            super( context, DBNAME, null, DBVERSION );
      }

      @Override
      public void onCreate( SQLiteDatabase db )
      {
            try
            {
                  db.execSQL( "CREATE TABLE " + DRUGTABLE + " (" + ID + " INTEGER PRIMARY KEY , " +
                              NAME + " TEXT , " +
                              MANUFACTURER + " TEXT , " +
                              FORM + " TEXT , " +
                              AVAILABLE + " TEXT , " +
                              PRICE + " TEXT " + ")" );
            }
            catch ( SQLException e )
            {
                  e.printStackTrace();
            }
      }

      @Override
      public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
      {
            try
            {
                  db.execSQL( "DROP TABLE IF EXISTS " + DRUGTABLE );
                  onCreate( db );
            }
            catch ( SQLException e )
            {
                  e.printStackTrace();
            }
      }

      // Adding new Drug
      public void addDrug( Drug drug )
      {
            try
            {

                  if ( !containes( drug ) )
                  {
                        SQLiteDatabase db = this.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put( ID, drug.getID() );
                        values.put( NAME, drug.getName() );
                        values.put( MANUFACTURER, drug.getManufacturer() );
                        values.put( FORM, drug.getForm() );
                        values.put( PRICE, drug.getPrice() );
                        values.put( AVAILABLE, drug.getAvailable() );

                        // Inserting Row
                        db.insert( DRUGTABLE, null, values );
                        db.close(); // Closing database connection
                  }
                  else
                  {
                        updateDrug( drug );
                  }

            }
            catch ( Exception e )
            {
                  e.printStackTrace();
            }
      }

      private void updateDrug( Drug drug )
      {

            try
            {
                  SQLiteDatabase db = this.getWritableDatabase();
                  ContentValues values = new ContentValues();
                  values.put( NAME, drug.getName() );
                  values.put( MANUFACTURER, drug.getManufacturer() );
                  values.put( FORM, drug.getForm() );
                  values.put( PRICE, drug.getPrice() );
                  values.put( AVAILABLE, drug.getAvailable() );

                  // Updating Row
                  db.update( DRUGTABLE, values, ID + " = " + drug.getID(), null );
                  db.close(); // Closing database connection
            }
            catch ( Exception e )
            {
                  e.printStackTrace();
            }
      }

      private boolean containes( Drug drug )
      {
            try
            {
                  String countQuery = "SELECT  * FROM " + DRUGTABLE + " WHERE " + ID + " = '" + drug.getID() + "'";
                  SQLiteDatabase db = this.getReadableDatabase();
                  Cursor cursor = db.rawQuery( countQuery, null );
                  int count = cursor.getCount();
                  if ( count == 0 )
                  {
                        return false;
                  }
                  else
                  {
                        return true;
                  }
            }
            catch ( Exception e )
            {
                  e.printStackTrace();
                  return false;
            }
      }

      // Getting Drug Count
      public int getDrugCount()
      {
            try
            {
                  String countQuery = "SELECT  * FROM " + DRUGTABLE;
                  SQLiteDatabase db = this.getReadableDatabase();
                  Cursor cursor = db.rawQuery( countQuery, null );
                  int count = cursor.getCount();
                  cursor.close();
                  // return count
                  return count;
            }
            catch ( Exception e )
            {
                  e.printStackTrace();
            }
            return 1;
      }

      public Drug getDrug( int position )
      {
            try
            {
                  String selectQuery = "SELECT * FROM " + DRUGTABLE + " LIMIT 1 OFFSET " + position;
                  SQLiteDatabase db = this.getWritableDatabase();
                  Cursor cursor = db.rawQuery( selectQuery, null );
                  if ( cursor != null )
                  {
                        cursor.moveToFirst();
                        Drug drug = new Drug();
                        drug.setName( cursor.getString( cursor.getColumnIndex( NAME ) ) );
                        drug.setID( cursor.getString( cursor.getColumnIndex( ID ) ) );
                        drug.setAvailable( cursor.getString( cursor.getColumnIndex( AVAILABLE ) ) );
                        drug.setPrice( cursor.getString( cursor.getColumnIndex( PRICE ) ) );
                        drug.setManufacturer( cursor.getString( cursor.getColumnIndex( MANUFACTURER ) ) );
                        drug.setForm( cursor.getString( cursor.getColumnIndex( FORM ) ) );
                        cursor.close();
                        return drug;
                  }

            }
            catch ( Exception e )
            {
                  e.printStackTrace();
            }
            return null;
      }
}