package com.example.chapter_6_allminitask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.chapter_6_allminitask.databinding.ActivityThread2Binding
import kotlinx.coroutines.*
import java.lang.Thread
import java.lang.ref.WeakReference

class Thread2 : AppCompatActivity() {
    private lateinit var binding: ActivityThread2Binding
    private lateinit var handler: Handler
    private var mvVariable = 10
    private lateinit var task: myAsyncTask
    private lateinit var cancelTask: myAsyncTask

    private lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThread2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val message = msg.obj as String
                binding.textView.text = message
            }
        }

        binding.btnThread1.setOnClickListener {
            thread1()
        }
        binding.btnThread2.setOnClickListener {
            thread2()
        }

        binding.btnThread3.setOnClickListener {
            thread3()
            //slide
        }

        binding.btnAsynctask.setOnClickListener {
            task = myAsyncTask(this)
            task.execute(10)
        }
        binding.btnCancelAsyntask.setOnClickListener {
//            cancelTask = myAsyncTask().cancel(true)
            //true nya itu bisa di cancel kalo mau di cancel pas download/ lagi berjalan
            // false itu harus ngelarin satu kali jalan baru berhenti
        }

        binding.btnCancel.setOnClickListener {
            val cancel = task.cancel(true)
        }
        binding.btnLaunch.setOnClickListener {
            scope = CoroutineScope(Dispatchers.Main)
            // menjalankan coroutine baru di background dan dilanjutkan
            scope.launch {
                // launck ini fire-forget, gk ada kembalian, beda kek async
                // non-blocking delay selama 1 detik (satuan waktu default adalah ms)
                delay(1000)
                // print setelah delay
                println("Coroutines!!")
            }
            // main thread berlanjut sementara corountines sedang delayed
            println("Hello,")
            // block main therad selama 2 detik untuk menjaga agar JVM tetap hidup
            Thread.sleep(2000)
            println("End")
//            scope.cancel()
        }
        binding.btnAsync.setOnClickListener {
            runBlocking(Dispatchers.Default) {
                // Dispatchers ini buat penentu coroutines kita mau di jalanin dmn
                // Semua corountine harus berjalan di dispatcher
                // jadi yg default itu akan di block kalo kita make runBlocking
                // kalo dispatcher gk di set, auto ke default
                val first = async { getNumber() }
                val result =
                    first.await() //await = menunggu sampe proses nya selesai, baru ntar hasilnya masuk ke result
                println("result coroutines : $result")
            }

            //bisa ditulis gini jg
//            GlobalScope.async {
//
//            }
        }
    }

    private suspend fun getNumber(): Int {
        // suspend function ini bisa dipanggil sama coroutines/ suspend function lain
        // intinya kalo mau manggil function ke coroutines harus make suspend function
        delay(1000)
        return 4 * 3
    }

    fun thread1() {
        Thread({
            val text = "Binar Academy"
            binding.textView.text = text
        }).start()
    }

    fun thread2() {
        println("testes")
        Thread({
            val text = "Binar Academy"
            binding.textView.post({
                binding.textView.text = text
            })

            println("testes2")
            Thread.sleep(2000)
            println("testes3")
        }).start()
        println("testes4")
    }

    fun thread3() {
        Thread({
            run {
                val text = "Binar Academy"
                val msg = Message.obtain()
                msg.obj = text
                msg.target = handler
                msg.sendToTarget()
            }
        }).start()
    }

    class myAsyncTask internal constructor(context: Thread2) :
        AsyncTask<Int, String, String?>() { // doInB , onProgUp, onPostExc

        private var resp: String? = null
        private val activityReference: WeakReference<Thread2> = WeakReference(context)

        override fun onPreExecute() {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return
            activity.binding.progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Int?): String? {
            publishProgress("Sleeping Started") // Calls onProgressUpdate()
            try {
                val time = params[0]?.times(1000)
                time?.toLong()?.let { java.lang.Thread.sleep(it / 2) }
                publishProgress("Half Time") // Calls onProgressUpdate()
                time?.toLong()?.let { java.lang.Thread.sleep(it / 2) }
                publishProgress("Sleeping Over") // Calls onProgressUpdate()
                resp = "Android was sleeping for " + params[0] + " seconds"
            } catch (e: InterruptedException) {
                e.printStackTrace()
                resp = e.message
            } catch (e: Exception) {
                e.printStackTrace()
                resp = e.message
            }

            return resp
        }


        override fun onPostExecute(result: String?) {

            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return
            activity.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            activity.findViewById<TextView>(R.id.textViewAsyn).text = result.let { it }
            activity.mvVariable = 100
        }

        override fun onProgressUpdate(vararg text: String?) {

            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            Toast.makeText(activity, text.firstOrNull(), Toast.LENGTH_SHORT).show()

        }
    }
}



