package com.example.covid19tracker.PackageSessionVariables;

public class ConstantsVolley {


    public static final String NONE = "None";

    public static final String COUNTRY= "country";

    public static final String COUNTRY_INFO = "countryInfo";
    public static final String ISO_2 = "iso2";
    public static final String ISO_3 = "iso3";
    public static final String FLAG = "flag";

    public static final String FOR_VALIDATION = "For validation";

    public static final String AFFECTED_COUNTRIES = "affectedCountries";
    public static final String CASES = "cases";
    public static final String ACTIVE = "active";
    public static final String DEATHS = "deaths";
    public static final String RECOVERED = "recovered";
    public static final String CRITICAL = "critical";
    public static final String TODAY_CASES = "todayCases";
    public static final String TODAY_DEATHS = "todayDeaths";
    public static final String TESTS = "tests";
    public static final String CASES_PER_ONE_MILLION = "casesPerOneMillion";
    public static final String DEATHS_PER_ONE_MILLION = "deathsPerOneMillion";
    public static final String UPDATED = "updated";
    public static final String TIMELINE = "timeline";

    public static final String CASES_2 = "confirmed";
    public static final String PUI_2 = "pui";
    public static final String PUM_2 = "pum";
    public static final String RECOVERED_2 = "recovered";
    public static final String DEATHS_2 = "deceased";
    public static final String TESTS_2 = "tests";

    public static final String CASE_ID = "caseID";
    public static final String AGE = "age";
    public static final String SEX = "sex";
    public static final String DATE_REPORTED = "date_reported";
    public static final String DATE_CONFIRMED = "date_confirmed";
    public static final String COORDINATES = "coordinates";
    public static final String STATUS = "status";
    public static final String NATIONALITY = "nationality";
    public static final String RESIDENCE = "residence";
    public static final String TRAVEL_DATE = "travel_date";
    public static final String TRAVEL_HISTORY = "travel_history";
    public static final String WHERE_NOW = "where_now";
    public static final String SYMPTOMS = "symptoms";
    public static final String FACILITY = "facility";
    public static final String DATE_DECEASED = "date_deceased";
    public static final String DATE_RECOVERED = "date_recovered";
    public static final String REMARKS = "remarks";
    public static final String METADATA = "metadata";
    public static final String RAW_DATA = "raw_data";
    public static final String MESSAGE = "message";

    private static final String BASE_URL = "https://corona.lmao.ninja/v2/";
    private static final String BASE_URL_2 = "https://ncovph.com/api/";

    public static final String CURRENT_STATUS = BASE_URL + "all";
    public static final String WORLD_STATUS = BASE_URL + "all";    // "countries/world";
    public static final String MY_COUNTRY_STATUS = BASE_URL + "countries/philippines";

    public static final String COUNTRIES = BASE_URL + "countries?sort=" + COUNTRY;

    public static final String COUNTRIES_MOST_CASES = BASE_URL + "countries?sort=" + CASES;
    public static final String COUNTRIES_MOST_ACTIVE = BASE_URL + "countries?sort=" + ACTIVE;
    public static final String COUNTRIES_MOST_DEATHS = BASE_URL + "countries?sort=" + DEATHS;
    public static final String COUNTRIES_MOST_RECOVERED = BASE_URL + "countries?sort=" + RECOVERED;
    public static final String COUNTRIES_MOST_CRITICAL = BASE_URL + "countries?sort=" + CRITICAL;

    public static final String COUNTRIES_MOST_TODAY_CASES = BASE_URL + "countries?sort=" + TODAY_CASES;
    public static final String COUNTRIES_MOST_TODAY_DEATHS = BASE_URL + "countries?sort=" + TODAY_DEATHS;

    public static final String HISTORICAL = BASE_URL + "historical/";
    public static final String HISTORICAL_WORLD = HISTORICAL + "all";

    public static final String MY_COUNTRY_CASES = BASE_URL_2 + "confirmed-cases";
    public static final String MY_COUNTRY_CASES_OFW = BASE_URL_2 + "ofw-cases";
    public static final String MY_COUNTRY_CASES_FOREIGN_NATIONAL = BASE_URL_2 + "foreign-national-cases";


}
