//package com.example.chapter_6_allminitask
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.SystemClock.sleep
//import android.view.View
//import android.widget.Button
//import android.widget.ProgressBar
//import android.widget.TextView
//import android.widget.Toast
//import com.example.chapter_6_allminitask.databinding.ActivityAsyncTaskBinding
//import java.lang.ref.WeakReference
//
//class AsyncTask : AppCompatActivity() {
//
//    private lateinit var binding: ActivityAsyncTaskBinding
//    var myVariable = 10
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAsyncTaskBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val button = findViewById<Button>(R.id.btnDoAsync)
//        button.setOnClickListener {
//            val task = MyAsyncTask(this)
//            task.execute(10)
//        }
//
//    }
//
//
//    companion object {
//        class MyAsyncTask internal constructor(context: MainActivity) :
//            AsyncTask<Int, String, String?>() {
//
//            private var resp: String? = null
//            private val activityReference: WeakReference<MainActivity> = WeakReference(context)
//
//            override fun onPreExecute() {
//                val activity = activityReference.get()
//                if (activity == null || activity.isFinishing) return
//                activity.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
//            }
//
//            override fun doInBackground(vararg params: Int?): String? {
//                publishProgress("Sleeping Started") // Calls onProgressUpdate()
//                try {
//                    val time = params[0]?.times(1000)
//                    time?.toLong()?.let { java.lang.Thread.sleep(it / 2) }
//                    publishProgress("Half Time") // Calls onProgressUpdate()
//                    time?.toLong()?.let { java.lang.Thread.sleep(it / 2) }
//                    publishProgress("Sleeping Over") // Calls onProgressUpdate()
//                    resp = "Android was sleeping for " + params[0] + " seconds"
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                    resp = e.message
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    resp = e.message
//                }
//
//                return resp
//            }
//
//
//            override fun onPostExecute(result: String?) {
//
//                val activity = activityReference.get()
//                if (activity == null || activity.isFinishing) return
//                activity.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
//                activity.findViewById<TextView>(R.id.textViewAsyn).text = result.let { it }
//                activity.myVariable = 100
//            }
//
//            override fun onProgressUpdate(vararg text: String?) {
//
//                val activity = activityReference.get()
//                if (activity == null || activity.isFinishing) return
//
//                Toast.makeText(activity, text.firstOrNull(), Toast.LENGTH_SHORT).show()
//
//            }
//        }
//    }
//}