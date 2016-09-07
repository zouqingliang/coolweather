package com.liang.pro.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liang.pro.coolweather.model.City;
import com.liang.pro.coolweather.model.County;
import com.liang.pro.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class CoolWeatherDB {

    private static final String DB_NAME = "cool_weather";
    private static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase sqLiteDatabase;

    private CoolWeatherDB(Context mContext) {
        CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(mContext,DB_NAME,null,VERSION);
        sqLiteDatabase = coolWeatherOpenHelper.getWritableDatabase();
    }

    /**
     * 获取实例
     * @param mContext
     * @return
     */
    public synchronized static CoolWeatherDB getInstance(Context mContext){
        if(coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(mContext);
        }
        return coolWeatherDB;
    }

    /**
     * 将Province实例存入到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            sqLiteDatabase.insert("province",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有省份信息
     * @return
     */
    public List<Province> loadProvinve(){
        List<Province> provinceList = new ArrayList<Province>();
        Cursor cursor = sqLiteDatabase.query("province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            } while (cursor.moveToNext());
        }
        return provinceList;
    }

    /**
     * 将City实例存入到数据库
     * @param city
     */
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceID());
            sqLiteDatabase.insert("city",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有城市信息
     * @return
     */
    public List<City> loadCity(){
        List<City> cityList = new ArrayList<City>();
        Cursor cursor = sqLiteDatabase.query("city",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceID(cursor.getInt(cursor.getColumnIndex("province_id")));
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        return cityList;
    }

    /**
     * 将County实例存入到数据库
     * @param county
     */
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityID());
            sqLiteDatabase.insert("county",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有县信息
     * @return
     */
    public List<County> loadCounty(){
        List<County> countyList = new ArrayList<County>();
        Cursor cursor = sqLiteDatabase.query("county",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityID(cursor.getInt(cursor.getColumnIndex("city_id")));
                countyList.add(county);
            } while (cursor.moveToNext());
        }
        return countyList;
    }
}
