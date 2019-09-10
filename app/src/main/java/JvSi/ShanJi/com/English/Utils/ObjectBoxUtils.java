package JvSi.ShanJi.com.English.Utils;

public class ObjectBoxUtils {/*



    *//**
     * 添加数据
     * *//*
    public static <T> void addData(Object o, Class c){

        Box<T> box= ObjectBox.getBoxStore().boxFor(c);


        box.put((T) o);

    }


    *//***
     * 获取指定查询数据
     * *//*
    public static <T> T getConditionsData(Class clazz,String key){

        Field[] fs = clazz.getDeclaredFields();

        for (int i = 0; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true);

            if (f.getName().endsWith(key)) {
                try {
                    long id = (long) f.get(clazz);
                    Box<T> box= ObjectBox.getBoxStore().boxFor(clazz);
                    return box.get(id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;

    }


    public static  <T> List<T> getAllData(Class clazz){

        Box<T> box= ObjectBox.getBoxStore().boxFor(clazz);

        return box.getAll();
    }



    //删除数据
    public static <T> void deleteData(Object o,Class clazz){

        Box <T> box= ObjectBox.getBoxStore().boxFor(clazz);

        box.remove((T) o);

    }


    *//**
     * 删除数据
     * *//*
    public static <T>  void removeAllData(Class clazz){

        Box <T> box= ObjectBox.getBoxStore().boxFor(clazz);
        box.removeAll();
    }
*/


}