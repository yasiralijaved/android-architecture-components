package com.yasiralijaved.android.arc.component.http.response;

public class Address
{
    private String zipcode;

    private Geo geo;

    private String suite;

    private String city;

    private String street;

    public String getZipcode ()
    {
        return zipcode;
    }

    public void setZipcode (String zipcode)
    {
        this.zipcode = zipcode;
    }

    public Geo getGeo ()
    {
        return geo;
    }

    public void setGeo (Geo geo)
    {
        this.geo = geo;
    }

    public String getSuite ()
    {
        return suite;
    }

    public void setSuite (String suite)
    {
        this.suite = suite;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    @Override
    public String toString()
    {
        return "Address [zipcode = "+zipcode+", geo = "+geo+", suite = "+suite+", city = "+city+", street = "+street+"]";
    }
}
