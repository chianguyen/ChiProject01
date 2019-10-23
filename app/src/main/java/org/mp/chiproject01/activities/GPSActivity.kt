package org.mp.chiproject01.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gps.*
import org.mp.chiproject01.R
import java.io.IOException
import java.util.*

private const val PERMISSION_REQUEST = 10


class GPSActivity : AppCompatActivity() {

    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var locationGps: Location? = null
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private var geocoderHandler: GeocoderHandler = GeocoderHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)

        //Disable this view if the user doesn't give permission
        disableView()

        //Ask for permission based on SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {
                enableView()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        } else {
            enableView()
        }

        gps_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                getLocation()
            }
        })

        gps_proceed.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var i = Intent(this@GPSActivity, ThankYouActivity::class.java)
                startActivity(i)
            }
        })


    }

    //So guys this just to show/hide the view
    private fun disableView() {
        gps_btn.isEnabled = false
        gps_btn.alpha = 0.5F
    }

    private fun enableView() {
        gps_btn.isEnabled = true
        gps_btn.alpha = 1F
        gps_btn.setOnClickListener { getLocation()}
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
    }

    //check permission
    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    //On the result of the permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess)
                enableView()

        }
    }

    //Get the location
    @SuppressLint("MissingPermission")
    private fun getLocation(){

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        //If there is gps
        if(hasGps){

            if(hasGps) {

                Log.d("CodeAndroidLocation", "hasGps")

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object :
                    LocationListener {

                    override fun onLocationChanged(location: Location?) { }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

                    override fun onProviderEnabled(provider: String?) {}

                    override fun onProviderDisabled(provider: String?) {}

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                if (localGpsLocation != null)
                    locationGps = localGpsLocation

            }

            if(locationGps!=null){
                Log.d("CodeAndroidLocation", " GPS Latitude: " + locationGps!!.latitude)
                Log.d("CodeAndroidLocation", " GPS Longitude: " + locationGps!!.longitude)

                getAddressFromLocation(locationGps!!, this,  geocoderHandler)

            }

        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }

    }

    //Get address from the location
    fun getAddressFromLocation(location: Location, context: Context, handler: Handler) {

        var sp: SharedPreferences = getSharedPreferences("gps_loc", Context.MODE_PRIVATE)

        var editor = sp.edit()

        val thread = object : Thread() {

            override fun run() {

                val geocoder = Geocoder(context, Locale.getDefault())

                var result: String? = null

                try {
                    val list = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (list != null && list.size > 0) {
                        val address = list[0]
                        result = address.getAddressLine(0)

                        editor.putString("address_loc", result)
                    }
                }

                catch (e: IOException) {
                    Log.e("error", "Impossible to connect to Geocoder", e)
                }

                finally {
                    val msg = Message.obtain()
                    msg.target = handler
                    if (result != null) {
                        msg.what = 1
                        val bundle = Bundle()
                        bundle.putString("address", result)
                        msg.data = bundle
                    } else
                        msg.what = 0
                    msg.sendToTarget()
                }
            }
        }
        thread.start()
    }


    @SuppressLint("HandlerLeak")
    private inner class GeocoderHandler : Handler() {

        override fun handleMessage(message: Message) {

            val result: String?

            when (message.what) {
                1 -> {
                    val bundle = message.data
                    result = bundle.getString("address")
                }
                else -> result = null
            }

            gps_result.text = result
        }
    }

}
