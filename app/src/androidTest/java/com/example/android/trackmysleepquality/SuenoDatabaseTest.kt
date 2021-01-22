
package com.example.android.trackmysleepquality

//import androidx.room.Room
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.android.trackmysleepquality.basededatos.SuenoDatabase
//import com.example.android.trackmysleepquality.basededatos.SuenoDatabaseDAO
//import com.example.android.trackmysleepquality.basededatos.SuenoNoche
//import org.junit.Assert.assertEquals
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.io.IOException
//
///**
// * This is not meant to be a full set of tests. For simplicity, most of your samples do not
// * include tests. However, when building the Room, it is helpful to make sure it works before
// * adding the UI.
// */
//
//@RunWith(AndroidJUnit4::class)
//class SuenoDatabaseTest {
//
//    private lateinit var sleepDao: SuenoDatabaseDAO
//    private lateinit var db: SuenoDatabase
//
//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        // Using an in-memory database because the information stored here disappears when the
//        // process is killed.
//        db = Room.inMemoryDatabaseBuilder(context, SuenoDatabase::class.java)
//                // Allowing main thread queries, just for testing.
//                .allowMainThreadQueries()
//                .build()
//        sleepDao = db.suenoDatabaseDAO
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    suspend fun insertAndGetNight() {
//        val night = SuenoNoche()
//        sleepDao.insertar(night)
//        val tonight = sleepDao.obtenerUltimaNoche()
//        assertEquals(tonight?.suenoCalidad, -1)
//    }
//}

