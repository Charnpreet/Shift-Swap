package com.example.charnpreet.shiftswap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;
import android.support.annotation.Nullable;
import android.util.Log;

import java.sql.Time;

public class DatabaseHelper extends SQLiteOpenHelper {
    private  Context context;
    private static final String DATABASE_NAME = "EmployeesDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String Employee_Table = "Employee";
    private static final String Employee_Credential_Table = "Employee_Credential";
    private static final String Company_Table ="Company";
    private static final String Comapny_Location_Table = "Location";
    private static final String Employee_position_Table = "Position";
    private static final String Employee_Avaialability_Table = "availability";
    private static final String Days_Of_Week = "Days_Of_Week";
    private static final String Employee_NO = "Employee_No";
    private static final String Employee_Name = "Employee_Name";
    private static final String Company_ID = "Company_ID";
    private static final String Position_ID="Position_ID";
    private static final String Employee_Password = "Employee_Password";
    private static final String Company_Name = "Company_Name";
    private static final String Company_location_Id="Company_location_Id";
    private static final String Company_location_name ="Company_location_Name";
//  private static final String Company_location_State ="Company_location_State";
    private static final String Position_Name="Position_Name";
    private static final String EmployeeAvailability_Id= "Availability_Id";
    private static final String Employee_AM_Availability="AM_Availability";
    private static final String Employee_PM_Availability="PM_Availability";
    private static final String Employee_ND_Availability="ND_Availability";
    private static final String Employee_AMPM_Availability="AMPM_Availability";
    private static final String Employee_AMND_Availability="AMND_Availability";
    private static final String Employee_PMND_Availability="PMND_Availability";
    private static final String Day_ID= "Day_ID";
    private static final String Day_Name="day_Name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
<<<<<<< HEAD
        ADDIngVAluesToDatabase();
        Log.i("tag", context.getDatabasePath(DATABASE_NAME).toString());

    }

    private void ADDIngVAluesToDatabase(){

        final String[] companyOptions = new String[]{"Select Your Company","Regis","Blue Cross","Buppa"};
        final  String[] locationOptions= new String[]{"Select Location", "Malvern East","Blackburne","Dandenong"};
        final String[] companyPositionOptions = new String[]{"Select Your Positon", "Pca","Rn","En","Fsa"};
        final  String[] daysOfWeek= new String[]{"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
       // try{
            for(int i=0; i<companyOptions.length; i++) {
                if (AddToCompany(i, companyOptions[i], i)) {
                    Log.i("tag", "values added successfully");
                }
            }
            for(int i =0; i<locationOptions.length; i++){
                if (AddToCompanyLocation(i, locationOptions[i])){
                    Log.i("tag", "Location saved");
                }
            }

            for(int i =0; i<companyPositionOptions.length; i++){
                if(AddToEmployeePosiiton(i, companyPositionOptions[i])){
                    Log.i("tag", "Position saved");
                }
            }
            for(int i = 0; i<daysOfWeek.length; i++){
                if(AddToDaysOfWeek(i, daysOfWeek[i])){
                    Log.i("tag", "Days saved");
                }
            }
            for(int i =0; i<daysOfWeek.length; i++){
               if(AddToEmployeeAvailability( 0,0,0,0,0,0,i)){
                   Log.i("tag", "avialbility  saved");
               }
        }
        //}//catch (Exception ex){
         //   Log.i("tag", ex.getMessage());
       // }

=======
        //SQLiteDatabase db = getWritableDatabase();
        Log.i("tag", context.getDatabasePath(DATABASE_NAME).toString());

>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("tag", "on create method");
       String sql = CreatingEmployeeTable();
            sqLiteDatabase.execSQL(sql);
        sql = CreateEmployeeCredentialTable();
            sqLiteDatabase.execSQL(sql);
        sql = CreateCompanyTable();
            sqLiteDatabase.execSQL(sql);
        sql = CreateLocationTable();
            sqLiteDatabase.execSQL(sql);
        sql = CreateEmployeePositionTable();
            sqLiteDatabase.execSQL(sql);
        sql =CreateEmployeeAvailabilityTable();
            sqLiteDatabase.execSQL(sql);
        sql =CreateDaysOfWeekTable();
            sqLiteDatabase.execSQL(sql);
<<<<<<< HEAD

=======
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //
    // creating EmployeeTable Table
    private String CreatingEmployeeTable(){

        String sql = "CREATE TABLE IF NOT EXISTS " + Employee_Table + " (\n" +
<<<<<<< HEAD
                "    " + Employee_NO + " INTEGER NOT NULL CONSTRAINT Employee_pk PRIMARY KEY,\n" +
=======
                "    " + Employee_NO + " INTEGER NOT NULL CONSTRAINT Employee_pk PRIMARY KEY AUTOINCREMENT,\n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                "    " + Employee_Name + " varchar(200) NOT NULL,\n" +
                "    " + Company_ID + " INTEGER NOT NULL,\n" +
                "    " + Position_ID + " INTEGER NOT NULL \n" +
                ");";
                return sql;

    }
    //
    // creating CreateEmployeeCredentialTable()
    private String CreateEmployeeCredentialTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Employee_Credential_Table + " (\n" +
<<<<<<< HEAD
                "    " + Employee_NO + " INTEGER NOT NULL CONSTRAINT Employee_Credential_pk PRIMARY KEY,\n" +
=======
                "    " + Employee_NO + " INTEGER NOT NULL CONSTRAINT Employee_Credential_pk PRIMARY KEY AUTOINCREMENT,\n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                "    " + Employee_Password + " varchar(200) NOT NULL \n" +
                ");";
        return sql;
    }
    //
    // creating Company_Table
    private String CreateCompanyTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Company_Table  + " (\n" +
<<<<<<< HEAD
                "    " + Company_ID+ " INTEGER NOT NULL CONSTRAINT Company_pk PRIMARY KEY,\n" +
=======
                "    " + Company_Id+ " INTEGER NOT NULL CONSTRAINT Company_pk PRIMARY KEY AUTOINCREMENT,\n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                "    " + Company_Name  + " varchar(200) NOT NULL,\n" +
                "    " + Company_location_Id + " INTEGER NOT NULL \n" +
                ");";
        return sql;
    }
    //
    // creating Comapny_Location_Table
    private String CreateLocationTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Comapny_Location_Table + " (\n" +
<<<<<<< HEAD
                "    " + Company_location_Id+ " INTEGER NOT NULL CONSTRAINT Location_pk PRIMARY KEY,\n" +
                "    " + Company_location_name  + " varchar(200) NOT NULL \n" +
=======
                "    " + Company_location_Id+ " INTEGER NOT NULL CONSTRAINT Location_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + Company_location_name  + " varchar(200) NOT NULL,\n" +
                "    " + Company_location_State  + " varchar(200) NOT NULL \n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                ");";
        return sql;
    }
    //
    // creating Employee_position_Table
    private String CreateEmployeePositionTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Employee_position_Table + " (\n" +
<<<<<<< HEAD
                "    " + Position_ID+ " INTEGER NOT NULL CONSTRAINT Position_pk PRIMARY KEY,\n" +
=======
                "    " + Position_ID+ " INTEGER NOT NULL CONSTRAINT Position_pk PRIMARY KEY AUTOINCREMENT,\n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                "    " + Position_Name + " varchar(200) NOT NULL \n" +
                ");";
        return sql;
    }
    //
    // creating EmployeeAvailabilityTable
    private String CreateEmployeeAvailabilityTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Employee_Avaialability_Table + " (\n" +
<<<<<<< HEAD
                "    " + Day_ID  + " INTEGER NOT NULL CONSTRAINT availability_pk PRIMARY KEY,\n" +
                "    " + Employee_AM_Availability  + " INTEGER NOT NULL DEFAULT 0,\n" +
                "    " + Employee_PM_Availability  + " INTEGER NOT NULL DEFAULT 0,\n" +
                "    " + Employee_ND_Availability  + " INTEGER NOT NULL DEFAULT 0,\n" +
                "    " + Employee_AMPM_Availability  + " INTEGER NOT NULL DEFAULT 0,\n" +
                "    " + Employee_AMND_Availability  + " INTEGER NOT NULL DEFAULT 0,\n" +
                "    " + Employee_PMND_Availability  + " INTEGER NOT NULL DEFAULT 0 \n" +
=======
                "    " + EmployeeAvailability_Id + " INTEGER NOT NULL CONSTRAINT availability_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + EmployeeAvailability_Time  + " Time NOT NULL,\n" +
                "    " + Day_ID  + " INTEGER NOT NULL \n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                ");";
        return sql;
    }
    //
    //creating DaysOfWeekTable
    private  String CreateDaysOfWeekTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + Days_Of_Week + " (\n" +
<<<<<<< HEAD
                "    " + Day_ID + " INTEGER NOT NULL CONSTRAINT Days_Of_Week_pk PRIMARY KEY,\n" +
=======
                "    " + Day_ID + " INTEGER NOT NULL CONSTRAINT Days_Of_Week_pk PRIMARY KEY AUTOINCREMENT,\n" +
>>>>>>> 657ba25459bae17c0eb4c05c2fc44de3312b43ee
                "    " + Day_Name  + " varchar(200) NOT NULL \n" +
                ");";
        return sql;

    }
//    //
//    // below method will be used to update employee availability
//    boolean updateAvilability(int avai_id, String time, int day_id) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(EmployeeAvailability_Id,avai_id);
//        contentValues.put(EmployeeAvailability_Time,time); //need to fix time currently acepting string, need to changed to Time
//        contentValues.put(Day_ID,day_id);
//        return db.update(Employee_Avaialability_Table, contentValues, EmployeeAvailability_Id+ EmployeeAvailability_Time , new String[]{String.valueOf(time)}) == 1;
//    }
    //
    // this will return all values from Employees Table
    Cursor getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Employee_Table, null);
    }
    //
    // below method is used to add values to employee Table
    boolean AddToEmployee(int employee_No, String name, int company_ID, int position_ID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Employee_NO, employee_No);
        contentValues.put(Employee_Name , name);
        contentValues.put(Company_ID, company_ID);
        contentValues.put(Position_ID, position_ID);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Employee_Table, null, contentValues) != -1;
    }
    //
    //this method is used to add Employee Credentials to Employee_Credential Table
    boolean AddToEmployeeCredentialTable(int employee_No, String employee_Password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Employee_NO, employee_No);
        contentValues.put(Employee_Password, employee_Password);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Employee_Credential_Table , null, contentValues) != -1;

    }

    //
    //this method is used to add Company Details to Company_Table
    boolean AddToCompany(int id, String name, int loc_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Company_ID,id);
        contentValues.put(Company_Name,name);
        contentValues.put(Company_location_Id,loc_id);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Company_Table , null, contentValues) != -1;

    }
    //
    //this method is used to add cpmpany location to Comapny_Location_Table
    boolean AddToCompanyLocation(int loc_id, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Company_location_Id,loc_id);
        contentValues.put(Company_location_name,name);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Comapny_Location_Table , null, contentValues) != -1;

    }
    //
    //this method is used to add Employee postion in EMployee_Position_Table
    boolean AddToEmployeePosiiton(int pos_id, String pos_name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Position_ID,pos_id);
        contentValues.put(Position_Name,pos_name);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Employee_position_Table, null, contentValues) != -1;

    }
//    //
//    // this method is used to add EMployee availability to Employee_availability_table
    boolean AddToEmployeeAvailability(int am,int pm,int nd,int ampm,int amnd,int pmnd,int day_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Employee_AM_Availability ,am);
        contentValues.put(Employee_PM_Availability ,pm);
        contentValues.put(Employee_ND_Availability ,nd);
        contentValues.put(Employee_AMPM_Availability ,ampm);
        contentValues.put(Employee_AMND_Availability ,amnd);
        contentValues.put(Employee_PMND_Availability ,pmnd);
        contentValues.put(Day_ID,day_id);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Employee_Avaialability_Table, null, contentValues) != -1;

    }
    //
    //this method is used to add days of the week to table Days_of_week
    boolean AddToDaysOfWeek(int day_id, String day_name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Day_ID,day_id);
        contentValues.put(Day_Name,day_name);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Days_Of_Week, null, contentValues) != -1;

    }
    Cursor getCompanyNames() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Company_Name FROM " + Company_Table, null);
    }
    Cursor getCompanyNameID(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Company_ID FROM " + Company_Table + " WHERE Company_Name ='"+name+"'", null);
    }


    Cursor getCompanyLocations() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Company_location_name FROM " + Comapny_Location_Table, null);
    }
    //
    //
    Cursor getCompanyLocationID(String loc) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Company_location_ID FROM " + Comapny_Location_Table + " WHERE Company_location_name = '"+loc+"'" , null);
    }
    //
    //
    Cursor getCompanyPosition() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Position_Name FROM " + Employee_position_Table, null);
    }
    //
    //
    Cursor getCompanyPositionID(String positon) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Position_ID FROM " + Employee_position_Table + " WHERE Position_Name = '"+positon+"'", null);
    }
    Cursor LoginQuerry(int inputUname){
        SQLiteDatabase db = getReadableDatabase();
       return db.rawQuery("SELECT * FROM " + Employee_Credential_Table + " WHERE Employee_No = "+inputUname+"", null);
    }
    //
    //
    Cursor DaysOfWeekQuerry(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT Day_Name FROM " + Days_Of_Week, null);
    }

}


