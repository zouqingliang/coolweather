package com.liang.pro.coolweather.util;

import android.text.TextUtils;

import com.liang.pro.coolweather.db.CoolWeatherDB;
import com.liang.pro.coolweather.model.City;
import com.liang.pro.coolweather.model.County;
import com.liang.pro.coolweather.model.Province;

/**
 * Created by Administrator on 2016/9/7.
 * 解析返回来的省市县数据
 */
public class Utility {

    /**
     * 解析返回的省级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param coolWeatherDB
     * @param response
     * @param provinceID
     * @return
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceID){

        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceID(provinceID);

                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param coolWeatherDB
     * @param response
     * @param cityID
     * @return
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityID){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityID(cityID);

                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
