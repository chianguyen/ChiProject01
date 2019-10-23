package org.mp.chiproject01.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.mp.chiproject01.modules.ProductItem

class MyDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        public val DATABASE_VERSION = 4
        public val DATABASE_NAME = "pdctdatabase"
        public val TABLE_NAME = "product"
        public val KEY_ID = "id"
        public val KEY_NAME = "name"
        public val KEY_DES = "description"
        public val KEY_PRICE = "price"
        public val TOTAL = "total"
        public val KEY_IMG = "img"
    }


    override fun onCreate(db: SQLiteDatabase?) {

        //Create Database
        val CREATE_TABLE  =
            ("CREATE TABLE " + TABLE_NAME + "( " +
                    KEY_ID + " INTEGER, " +
                    KEY_NAME + " TEXT," +
                    KEY_PRICE + " TEXT, " +
                    KEY_DES + " INT" + " )")
//                    KEY_IMG + " TEXT" + " )")

        //Execute
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

        Log.d("upgrade", "onUpgrade() called")
    }


    fun addProduct(product: ProductItem): Long{

        val db = this.writableDatabase

        val contentValue = ContentValues()

        contentValue.put(KEY_ID, product.pId)
        contentValue.put(KEY_NAME, product.pName)
        contentValue.put(KEY_PRICE, product.pPrice)
        contentValue.put(KEY_DES, product.pDes)
    //    contentValue.put(KEY_IMG, product.pImg)

        val success = db.insert(TABLE_NAME, null, contentValue)
        db.close()

        Log.d("data", "Product added $success")
        return success  //entry number that got added
    }

    fun deleteProduct(id: Int): Int {
        val db = this.writableDatabase

        // receives the Id / entry number in the table, not the user defined Id number
        val success = db.delete(TABLE_NAME, "$KEY_ID = $id", null)
        db.close()
        Log.d("data", "Product deleted $success")
        return success
    }


    fun viewAllOrder(): ArrayList<ProductItem> {

        var productList : ArrayList<ProductItem> = ArrayList()

        val db = this.readableDatabase
        lateinit var cursor: Cursor

        val selectQuery = "SELECT * FROM $TABLE_NAME"

        //do try-catch for a rawQuery
        try {
            //can be used for insertion or deletion
            cursor = db.rawQuery(selectQuery, null)
        }

        catch (e: SQLException){
            Log.e("Error", e.toString())
        }

        if(cursor.moveToFirst()){
            var id: String
            var name: String
            var price: String
            var description: String
            var img: String

            do {
                id = cursor.getString(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                price = cursor.getString(cursor.getColumnIndex(KEY_PRICE))
                description = cursor.getString(cursor.getColumnIndex(KEY_DES))
             //   img = cursor.getString(cursor.getColumnIndex(KEY_IMG))

                productList.add(ProductItem(id, name, price, description, ""))

            }
            while (cursor.moveToNext())
        }
        db.close()
        return productList
    }

    fun deleteAll(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }

    fun getTotalPrice(): Int {
        val db = this.readableDatabase

        var total: Int = 0

        var cursor = db.rawQuery("SELECT SUM(" + KEY_PRICE + ") as Total FROM " + TABLE_NAME, null)

        if(cursor.moveToFirst()) {
            total = cursor.getInt(0) // get final total
            Log.i("TOTAL", total.toString())

        }

        return total

    }






}