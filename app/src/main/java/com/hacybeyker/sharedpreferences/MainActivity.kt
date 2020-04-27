package com.hacybeyker.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

const val TAG = "Here"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SharedPreferences Simple
        val startMillisSaveSimple: Long = System.currentTimeMillis()
        val sharedPreferencesSimple: SharedPreferences =
            applicationContext.getSharedPreferences(SPSIMPLE, Context.MODE_PRIVATE)
        sharedPreferencesSimple.edit().apply {
            putString(PRODUCT, "iPhone X")
            putFloat(PRICE, 1000.00f)
            putInt(STOCK, 500)
            putBoolean(STATE, true)
            apply()
        }
        val endMillisSaveSimple: Long = System.currentTimeMillis()
        Log.d(TAG, "This is a millis save simple: ${endMillisSaveSimple - startMillisSaveSimple}")

        val startMillisReadSimple: Long = System.currentTimeMillis()
        val product1 = sharedPreferencesSimple.getString(PRODUCT, "")
        val price1 = sharedPreferencesSimple.getFloat(PRICE, 0.0f)
        val stock1 = sharedPreferencesSimple.getInt(STOCK, 0)
        val state1 = sharedPreferencesSimple.getBoolean(STATE, false)
        val endMillisReadSimple: Long = System.currentTimeMillis()
        Log.d(
            TAG,
            "This is a millis read simple: ${endMillisReadSimple - startMillisReadSimple} \t $product1 - $price1 - $stock1 - $state1"
        )

        //SharedPreferences Encrypt
        val startMillisSaveEncrypt: Long = System.currentTimeMillis()
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferencesEncrypt = EncryptedSharedPreferences.create(
            SPENCRYPT,
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        sharedPreferencesEncrypt.edit().apply {
            putString(PRODUCT, "iPhone X")
            putFloat(PRICE, 1000.00f)
            putInt(STOCK, 500)
            putBoolean(STATE, true)
            apply()
        }
        val endMillisSaveEncrypt: Long = System.currentTimeMillis()
        Log.d(
            TAG,
            "This is a millis save encrypt: ${endMillisSaveEncrypt - startMillisSaveEncrypt}"
        )

        val startMillisReadEncrypt: Long = System.currentTimeMillis()
        val product = sharedPreferencesEncrypt.getString(PRODUCT, "")
        val price = sharedPreferencesEncrypt.getFloat(PRICE, 0.0f)
        val stock = sharedPreferencesEncrypt.getInt(STOCK, 0)
        val state = sharedPreferencesEncrypt.getBoolean(STATE, false)
        val endMillisReadEncrypt: Long = System.currentTimeMillis()
        Log.d(
            TAG,
            "This is a millis read encrypt: ${endMillisReadEncrypt - startMillisReadEncrypt} \t $product - $price - $stock - $state"
        )
    }
}
