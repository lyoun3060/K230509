package com.example.dbtest1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?):SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteOpenHelper){
        db.execSQL("create table"+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, ADDRESS TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME)
    }

    fun insertData(name:String?, phone:String?, address:String?):Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, phone)
        contentValues.put(COL_4, address)
    }
    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ? ", arrayOf(id))
    }

    //데이터베이스 수정하기
    fun updateData(id: String, name: String?, phone: String?, address: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, phone)
        contentValues.put(COL_4, address)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    companion object{
        const val DATABASE_NAME = "test"
        const val TABLE_NAME = "test_table"

        const val COL_1 = "id"
        const val COL_2 = "name"
        const val COL_3 = "phone"
        const val COL_4 = "address"

    }
}