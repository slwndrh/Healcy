package com.example.healcy.monitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.healcy.R
import com.example.healcy.ml.Model
import com.example.healcy.ml.ModelKontraksi
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class MonitoringActivity : AppCompatActivity() {
    private lateinit var rateMother: TextView
    private lateinit var rateTemp: TextView
    private lateinit var rateBaseline: TextView
    private lateinit var rateFetal: TextView
    private lateinit var rateUreter: TextView
    private lateinit var firebaseMother: Firebase
    private lateinit var firebaseTemp: Firebase
    private lateinit var firebaseBaseline: Firebase
    private lateinit var firebaseFetal: Firebase
    private lateinit var firebaseUreter: Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)

        supportActionBar?.title = getString(R.string.monitoring)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rateMother = findViewById(R.id.rate_mother)
        rateTemp = findViewById(R.id.rate_temp)
        rateBaseline = findViewById(R.id.value_baseline)
        rateFetal = findViewById(R.id.value_fetal)
        rateUreter = findViewById(R.id.value_ureter)

        firebaseMother = Firebase("https://healcy-388714-default-rtdb.firebaseio.com/Jantung")
        firebaseTemp = Firebase("https://healcy-388714-default-rtdb.firebaseio.com/Suhu")
        firebaseBaseline = Firebase("https://healcy-388714-default-rtdb.firebaseio.com/Kontraksi/baseline_value")
        firebaseFetal = Firebase("https://healcy-388714-default-rtdb.firebaseio.com/Kontraksi/fetal_movement")
        firebaseUreter = Firebase("https://healcy-388714-default-rtdb.firebaseio.com/Kontraksi/ureter_contraction")

        setPredictUterine()
        setPredictHeart()

        setMonitorMother()
        setMonitorTemp()
        setMonitorBaseline()
        setMonitorFetal()
        setMonitorUreter()
    }

    private fun setPredictHeart() {
        var btnHeart : Button = findViewById<Button>(R.id.btn_heart)
        btnHeart.setOnClickListener(View.OnClickListener {

            var ed4 : EditText = findViewById(R.id.edt_heart1)
            var ed5 : EditText = findViewById(R.id.edt_heart2)
            var ed6 : EditText = findViewById(R.id.edt_heart3)
            var ed7 : EditText = findViewById(R.id.edt_heart4)
            var ed8 : EditText = findViewById(R.id.edt_heart5)
            var ed9 : EditText = findViewById(R.id.edt_heart6)

            var v4 : Float = ed4.text.toString().toFloat()
            var v5 : Float = ed5.text.toString().toFloat()
            var v6 : Float = ed6.text.toString().toFloat()
            var v7 : Float = ed7.text.toString().toFloat()
            var v8 : Float = ed8.text.toString().toFloat()
            var v9 : Float = ed9.text.toString().toFloat()

            var byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(6*4)
            byteBuffer.putFloat(v4)
            byteBuffer.putFloat(v5)
            byteBuffer.putFloat(v6)
            byteBuffer.putFloat(v7)
            byteBuffer.putFloat(v8)
            byteBuffer.putFloat(v9)

            val model = Model.newInstance(this)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 6), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)

            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var tv1 : TextView = findViewById(R.id.output_heart)

            tv1.setText("Mid risk - " + outputFeature0.getFloatValue(0) + "\nLow risk - " +
                    outputFeature0.getFloatValue(1) +
                    "\nHigh Risk - " + outputFeature0.getFloatValue(2))

            model.close()

            val userId = "thu1HgmVGPNyAI9TkLWP"

            val firestore = FirebaseFirestore.getInstance()
            val collectionReference = firestore.collection("Users")
            val userDocument = collectionReference.document(userId)

            val outputData = tv1.text.toString()
            val data = mapOf("output" to outputData)
            userDocument.update(data)
        })
    }

    private fun setPredictUterine() {
        var btnUterine : Button = findViewById<Button>(R.id.btn_uterine)
        btnUterine.setOnClickListener(View.OnClickListener {

            var ed1 : EditText = findViewById(R.id.edt_uterine1)
            var ed2 : EditText = findViewById(R.id.edt_uterine2)
            var ed3 : EditText = findViewById(R.id.edt_uterine3)

            var v1 : Float = ed1.text.toString().toFloat()
            var v2 : Float = ed2.text.toString().toFloat()
            var v3 : Float = ed3.text.toString().toFloat()

            var byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(3*4)
            byteBuffer.putFloat(v1)
            byteBuffer.putFloat(v2)
            byteBuffer.putFloat(v3)
            val model = ModelKontraksi.newInstance(this)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)

            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

            var tv2 : TextView = findViewById(R.id.output_uterine)

            tv2.setText("Normal - " + outputFeature0[0].toString() + "\nLow Risk - " +
                    outputFeature0[1].toString() +
                    "\nHigh Risk - " + outputFeature0[2].toString())

            model.close()

            val userId = "thu1HgmVGPNyAI9TkLWP"

            val firestore = FirebaseFirestore.getInstance()
            val collectionReference = firestore.collection("Users")
            val userDocument = collectionReference.document(userId)

            val outputData = tv2.text.toString()
            val data = mapOf("output" to outputData)
            userDocument.update(data)
        })
    }

    private fun setMonitorUreter() {
        firebaseUreter.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val monitorUreter = dataSnapshot.getValue(String::class.java)
                rateUreter.text = monitorUreter
            }

            override fun onCancelled(firebaseError: FirebaseError) {

            }

        })
    }

    private fun setMonitorFetal() {
        firebaseFetal.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val monitorFetal = dataSnapshot.getValue(String::class.java)
                rateFetal.text = monitorFetal
            }

            override fun onCancelled(firebaseError: FirebaseError) {

            }

        })
    }

    private fun setMonitorBaseline() {
        firebaseBaseline.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val monitorBaseline = dataSnapshot.getValue(String::class.java)
                rateBaseline.text = monitorBaseline
            }

            override fun onCancelled(firebaseError: FirebaseError) {

            }

        })
    }

    private fun setMonitorTemp() {
        firebaseTemp.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val monitorTemp = dataSnapshot.getValue(String::class.java)
                rateTemp.text = monitorTemp
            }

            override fun onCancelled(firebaseError: FirebaseError) {

            }

        })
    }

    private fun setMonitorMother() {
        firebaseMother.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val monitorMother = dataSnapshot.getValue(String::class.java)
                rateMother.text = monitorMother
            }

            override fun onCancelled(firebaseError: FirebaseError) {

            }

        })
    }
}