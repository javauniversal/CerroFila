package com.appgestor.cerrofilas.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    private Context context;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String sqlRuta = context.getString(R.string.query_ruta);

        String sqlCenso = context.getString(R.string.query_censo);

        String sqlDetallRuta = context.getString(R.string.query_detalleRuta);

        String sqlMapPonit = context.getString(R.string.query_mapPoint);

        db.execSQL(sqlRuta);
        db.execSQL(sqlCenso);
        db.execSQL(sqlDetallRuta);
        db.execSQL(sqlMapPonit);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Ruta");
        db.execSQL("DROP TABLE IF EXISTS Censo");
        db.execSQL("DROP TABLE IF EXISTS DestalleRuta");
        db.execSQL("DROP TABLE IF EXISTS MapPoint");
        this.onCreate(db);
    }


    /*
    public boolean insertRuta (Ruta data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put("nombreRuta", data.get_nombre());
            values.put("fechainicial", String.valueOf(data.get_fechaInicio()));
            values.put("fechafinal", String.valueOf(data.get_fechaFin()));
            values.put("estadoruta", data.get_enumEstadoRuta());

            db.insert("Ruta", null, values);
            Log.d("Ruta", data.toString());
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }
        return true;
    }

    public boolean insertCenso (Censo data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put("idRuta", data.get_idRuta());
            values.put("tiempocenso", String.valueOf(data.get_tiempocenso()));
            values.put("latitud", data.get_latitud());
            values.put("longitud", data.get_longitud());
            values.put("censodata", data.get_censoData());

            db.insert("Censo", null, values);
            Log.d("Censo", data.toString());
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }
        return true;
    }

    public boolean insertDetalleRuta (DetalleRuta data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put("idRuta", data.get_idRuta());
            values.put("tiemposeguimiento", String.valueOf(data.get_tiempoSeguimiento()));
            values.put("latitud", data.get_latitud());
            values.put("longitud", data.get_longitud());

            db.insert("DetalleRuta", null, values);
            Log.d("DestalleRuta", data.toString());
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }
        return true;
    }

    public boolean insertMapPoint (MapPoint data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put("orden", data.get_orden());
            values.put("idRuta", data.get_idRuta());
            values.put("latitud", data.get_latitud());
            values.put("longitud", data.get_longitud());

            db.insert("MapPoint", null, values);
            Log.d("MapPoint", data.toString());
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }
        return true;
    }

    public List<DetalleRuta> getDetalleRuta(int idRuta){
        ArrayList<DetalleRuta> addmapPoint = new ArrayList<>();
        String sql = "SELECT * FROM DetalleRuta where idRuta = " + idRuta + " order by tiemposeguimiento";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        DetalleRuta mapPoint;
        if (cursor.moveToFirst()) {
            do {
                mapPoint = new DetalleRuta();
                mapPoint.set_idRuta(cursor.getInt(1));
                mapPoint.set_tiempoSeguimiento(cursor.getString(2));
                mapPoint.set_latitud(cursor.getDouble(3));
                mapPoint.set_longitud(cursor.getDouble(4));
                addmapPoint.add(mapPoint);
            } while(cursor.moveToNext());
        }
        return addmapPoint;
    }

    public List<Censo> getCensoRuta(int idRuta){
        String sql = "SELECT * FROM Censo where idRuta = " + idRuta + " order by tiempocenso";
        return getCensos(sql);
    }

    public List<Censo> getCensoRutaOrderByDesc(int idRuta){
        String sql = "SELECT * FROM Censo where idRuta = " + idRuta + " order by tiempocenso desc";
        return getCensos(sql);
    }

    public List<Censo> getCensos(String SQL) {
        ArrayList<Censo> addmapPoint = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);

        Censo mapPoint;
        if (cursor.moveToFirst()) {
            do {
                mapPoint = new Censo();
                mapPoint.set_idRuta(cursor.getInt(1));
                mapPoint.set_tiempocenso(cursor.getString(2));
                mapPoint.set_latitud(cursor.getDouble(3));
                mapPoint.set_longitud(cursor.getDouble(4));
                mapPoint.set_censoData(cursor.getString(5));
                addmapPoint.add(mapPoint);
            } while(cursor.moveToNext());
        }
        return addmapPoint;
    }

    public int getCountCensos(int idRuta) {

        String sql = "SELECT count(*) FROM Censo where idruta = "+idRuta;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public String getStarCensoDate(int idRuta) {

        String sql = "SELECT tiempocenso FROM Censo where idruta = "+ idRuta +" order by tiempocenso asc limit 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return null;
    }

    public String getEndCensoDate(int idRuta) {

        String sql = "SELECT tiempocenso FROM Censo where idruta = "+ idRuta +" order by tiempocenso desc limit 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }

        return null;
    }

    public List<MapPoint> getMapPoint(int idRuta){
        ArrayList<MapPoint> addmapPoint = new ArrayList<>();
        String sql = "SELECT * FROM MapPoint where idRuta = " + idRuta + " order by orden";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        MapPoint mapPoint;
        if (cursor.moveToFirst()) {
            do {
                mapPoint = new MapPoint();
                mapPoint.set_orden(cursor.getInt(1));
                mapPoint.set_idRuta(cursor.getInt(2));
                mapPoint.set_latitud(cursor.getDouble(3));
                mapPoint.set_longitud(cursor.getDouble(4));
                addmapPoint.add(mapPoint);
            } while(cursor.moveToNext());
        }
        return addmapPoint;
    }

    public List<Ruta> getRuta() {
        ArrayList<Ruta> addRutas = new ArrayList<>();
        String sql = "SELECT * FROM Ruta";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Ruta ruta;
        if (cursor.moveToFirst()) {
            do {
                ruta = new Ruta();
                ruta.set_idRuta(Integer.parseInt(cursor.getString(0)));
                ruta.set_nombre(cursor.getString(1));
                ruta.set_estadoRuta(cursor.getString(4));
                addRutas.add(ruta);
            } while(cursor.moveToNext());
        }
        return addRutas;
    }

    /*public List<Censo> getCenso() {
        ArrayList<Censo> addRutas = new ArrayList<>();
        String sql = "SELECT * FROM Censo";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Censo censo;
        if (cursor.moveToFirst()) {
            do {
                censo = new Censo();
                censo.set_idCenso(Integer.parseInt(cursor.getString(0)));
                censo.set_idRuta(Integer.parseInt(cursor.getString(1)));
                censo.set_tiempocenso(String.valueOf(cursor.getString(2)));
                censo.set_estadoRuta(cursor.getString(4));
                addRutas.add(ruta);
            } while(cursor.moveToNext());
        }

        return addRutas;
    }*/
    /*
    public boolean deleteRuta(){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("Ruta", null, null);
        db.close();
        return p > 0;
    }

    public boolean deleteCenso(){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("Censo", null, null);
        db.close();
        return p > 0;
    }

    public boolean deleteDetalleRuta(){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("DetalleRuta", null, null);
        db.close();
        return p > 0;
    }

    public boolean deleteMapPoint(){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("MapPoint", null, null);
        db.close();
        return p > 0;
    }

    public boolean updateInicioRuta(Ruta data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fechainicial", String.valueOf(data.get_fechaInicio()));
        values.put("estadoruta", String.valueOf(EstadoRutas.EnEjecucion));
        try {
            db.update("Ruta", values, "id = ?", new String[] {String.valueOf(data.get_idRuta())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }
        return true;
    }

    public boolean validarInicioRuta(String estado){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor;
        String columns[] = {"id", "nombreRuta", "fechainicial", "fechafinal", "estadoruta"};
        cursor = db.query("Ruta", columns, "estadoruta = ?", new String[] {estado}, null, null, null, null);
        return cursor.getCount() <= 0;
    }

    public boolean validarInicioRuta(int idRuta){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor;
        String columns[] = {"id", "nombreRuta", "fechainicial", "fechafinal", "estadoruta"};
        cursor = db.query("Ruta", columns, "estadoruta in('EnEjecucion','Finalizada') and id = ?", new String[] {String.valueOf(idRuta)}, null, null, null, null);
        return cursor.getCount() <= 0;
    }

    public boolean validarInicioRuta(String estado, int idRuta){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor;
        String columns[] = {"id", "nombreRuta", "fechainicial", "fechafinal", "estadoruta"};
        cursor = db.query("Ruta", columns, "estadoruta = ? and id = ?", new String[] {estado, String.valueOf(idRuta)}, null, null, null, null);
        return cursor.getCount() <= 0;
    }

    public boolean validateCanEndRoute(int idRuta){
        String sql = "SELECT * FROM Ruta WHERE id = " + idRuta + " and estadoruta = 'EnEjecucion'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return cursor.getCount() > 0;
    }

    public int idRuta(){
        int _idRuta = 0;
        String sql = "SELECT * FROM Ruta WHERE estadoruta = 'EnEjecucion' LIMIT 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                _idRuta = Integer.parseInt(cursor.getString(0));
            } while(cursor.moveToNext());
        }

        return _idRuta;
    }

    public int idRutaInsert(){
        int _idRuta = 0;
        String sql = "SELECT * FROM Ruta LIMIT 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                _idRuta = Integer.parseInt(cursor.getString(0));
            } while(cursor.moveToNext());
        }

        return _idRuta;
    }

    public boolean updateFinRuta(Ruta data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fechainicial", String.valueOf(data.get_fechaInicio()));
        values.put("estadoruta", String.valueOf(EstadoRutas.Finalizada));
        try {
            db.update("Ruta", values, "id = ?", new String[] {String.valueOf(data.get_idRuta())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }
        return true;
    }

    /*
    public boolean insertProduct(Product data){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put("codeproduct", data.getIdProduct());
            values.put("nameProduct", data.getNombre());
            values.put("precio", data.getPrecio());
            values.put("descripcion", data.getDescripcion());
            values.put("urlimagen", data.getImagemes());

            db.insert("carrito", null, values);
            Log.d("carrito", data.toString());
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }

        return true;
    }

    /*
    public Cursor getEstudiantesSp() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id AS _id, nombre FROM estudiantes";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public Cursor getMateriasSp(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT materias.id AS _id, materias.nombre AS nombre FROM materias WHERE materias.id NOT IN (SELECT idMateria FROM movimiento WHERE idEstuden = "+id+" )";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public boolean insetMovimiento(EntityMovimiento data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idEstuden", data.getIdEstudiante());
        values.put("jornada", data.getJornada());
        values.put("facultad", data.getFacultad());
        values.put("semestre", data.getSemestre());
        values.put("idMateria", data.getIdMateria());

        db.insert("movimiento", null, values);
        Log.d("Movimiento", data.toString());
        db.close();
        return true;
    }

    public void insertMaterias(EntityMateria data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", data.getIdMateria());
        values.put("nombre", data.getDescripcion());
        values.put("estado", data.getEstado());

        db.insert("materias", null, values);
        Log.d("materias", data.toString());
        db.close();
    }

    public ArrayList<EntityMateria> getMaterias() {
        ArrayList<EntityMateria> materias = new ArrayList<EntityMateria>();

        String sql = "SELECT * FROM materias";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        EntityMateria materia = null;
        if (cursor.moveToFirst()) {
            do {
                materia = new EntityMateria();
                materia.setIdMateria(Integer.parseInt(cursor.getString(0)));
                materia.setDescripcion(cursor.getString(1));
                materia.setEstado(Integer.parseInt(cursor.getString(2)));
                materias.add(materia);
            } while(cursor.moveToNext());
        }
        return materias;
    }

    public boolean eliminarMateria(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("materias", "id = ?", new String[] {String.valueOf(id)});
        db.close();
        if(p <= 0){
            return false;
        }
        return true;
    }

    public boolean updateEstudiante(EntityEstudiante data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", data.getNombre());
        values.put("apellido", data.getApellido());
        values.put("correo", data.getCorreo());
        values.put("telefono", data.getTelefono());
        values.put("edad", data.getEdad());

        try {
            db.update("estudiantes", values, "id = ?", new String[] {String.valueOf(data.getCodigo())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }

        return true;
    }

    public boolean updateMateria(EntityMateria data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", data.getDescripcion());

        try {
            db.update("materias", values, "id = ?", new String[] {String.valueOf(data.getIdMateria())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }

        return true;
    }
    */

}

