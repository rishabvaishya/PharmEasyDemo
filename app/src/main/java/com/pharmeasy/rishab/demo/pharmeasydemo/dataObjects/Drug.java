package com.pharmeasy.rishab.demo.pharmeasydemo.dataObjects;

public class Drug
{
      String ID           = "id";
      String name         = "name";
      String manufacturer = "manufacturer";
      String form         = "form";
      String available    = "available";
      String price        = "price";

      public String getForm()
      {
            return form;
      }

      public void setForm( String form )
      {
            this.form = form;
      }

      public String getID()
      {
            return ID;
      }

      public void setID( String ID )
      {
            this.ID = ID;
      }

      public String getName()
      {
            return name;
      }

      public void setName( String name )
      {
            this.name = name;
      }

      public String getManufacturer()
      {
            return manufacturer;
      }

      public void setManufacturer( String manufacturer )
      {
            this.manufacturer = manufacturer;
      }

      public String getAvailable()
      {
            return available;
      }

      public void setAvailable( String available )
      {
            this.available = available;
      }

      public String getPrice()
      {
            return price;
      }

      public void setPrice( String price )
      {
            this.price = price;
      }

      public boolean isAvalable()
      {
            if ( available.equalsIgnoreCase( "false" ) )
            {
                  return false;
            }
            else
            {
                  return true;
            }
      }

}
