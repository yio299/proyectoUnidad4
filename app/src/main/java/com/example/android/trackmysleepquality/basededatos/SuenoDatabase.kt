
package com.example.android.trackmysleepquality.basededatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuenoNoche::class], version = 1, exportSchema = false)
abstract class SuenoDatabase : RoomDatabase() {





    /**
     *  Conecta la database con el DAO
     */
    abstract val suenoDatabaseDAO: SuenoDatabaseDAO

    /**
     * Definir un companion object, esto nos permite agregar funciones en la clase SuenoDatabase.
     *
     *
     * Por ejemplo, los clientes pueden llamar a `SuenoDatabase.getInstance (context)` para crear una nueva instancia
     * de SuenoDatabase.
     */
    companion object {
        /**
         * INSTANCE mantendrá una referencia a cualquier base de datos que halla sido creada con este getInstance.
         *
         * Esto nos ayudará a evitar inicializar repetidamente la base de datos, lo cual es malo para el sistema.
         *
         *  El valor de una variable @Volatile nunca se almacenará en caché, y todas las escrituras y
         *  Las lecturas se realizarán desde y hacia la memoria principal. Significa que los cambios realizados por uno
         *  el hilo de los datos compartidos es visible para otros hilos.
         */
        @Volatile
        private var INSTANCE: SuenoDatabase? = null

        /**
         * Función que nos ayuda a obtener la base de datos.
         *
         * Si ya se ha instanciado una base de datos, se devolverá la base de datos anterior.
         * De lo contrario, se creara una nueva base de datos.
         */
        fun getInstance(context: Context): SuenoDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SuenoDatabase::class.java,
                            "sleep_history_database"
                    )
                            // Limpia y recompila la base de datos con migracion.
                            .fallbackToDestructiveMigration()
                            .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Retorna la instancia de la base de datos.
                return instance
            }
        }
    }
}
