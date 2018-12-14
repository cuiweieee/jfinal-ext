package com.masterc.jfinal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.masterc.jfinal.converter.ConverterException;
import com.masterc.jfinal.converter.JsonTypeConverter;
import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jfinal.render.RenderManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseController extends Controller {

    private static final RenderManager renderManager = RenderManager.me();

    private static JsonTypeConverter typeConverter = JsonTypeConverter.me();

    @NotAction
    protected JSONObject getJsonObject() {
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(getRawData());
        } catch (JSONException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter");
        }
        if (jsonObject == null) {
            return new JSONObject();
        }
        return jsonObject;
    }

    @Deprecated
    @NotAction
    protected JSONObject getJsonObject(String name) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return new JSONObject();
        }
        return jsonObject.getJSONObject(name);
    }

    @NotAction
    protected JSONArray getJsonArray() {
        JSONArray array;
        try {
            array = JSONArray.parseArray(getRawData());
        } catch (JSONException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter");
        }
        return array;
    }

    @NotAction
    protected JSONArray getJsonArray(String param) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return null;
        }
        if (jsonObject.isEmpty()) {
            return null;
        }
        return jsonObject.getJSONArray(param);
    }

    @NotAction
    protected Long[] getJsonParaValuesToLong(String param) {
        JSONArray jsonArray = getJsonArray(param);
        Long[] result;
        try {
            result = jsonArray.toArray(new Long[]{});
        } catch (Exception e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter to Long Array");
        }
        return result;
    }

    @NotAction
    protected Integer[] getJsonParaValuesToInt(String param) {
        JSONArray jsonArray = getJsonArray(param);
        Integer[] result;
        try {
            result = jsonArray.toArray(new Integer[]{});
        } catch (Exception e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter to Integer Array");
        }
        return result;
    }

    @NotAction
    protected String[] getJsonParaValues(String param) {
        JSONArray jsonArray = getJsonArray(param);
        String[] result;
        try {
            result = jsonArray.toArray(new String[]{});
        } catch (Exception e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter to String Array");
        }
        return result;
    }

    @NotAction
    protected Record getJsonRecord(String name) {
        JSONObject jsonObject = getJsonObject(name);
        if (jsonObject == null) {
            return null;
        }
        Record record = new Record();
        record.setColumns(jsonObject);
        return record;
    }

    @NotAction
    protected Record getJsonRecord() {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return null;
        }
        Record record = new Record();
        record.setColumns(jsonObject);
        return record;
    }

    @NotAction
    protected Record getRecord() {
        Map<String, String[]> map = getParaMap();
        Record record = new Record();
        for (Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getValue().length == 1) {
                record.set(entry.getKey(), entry.getValue()[0]);
            } else {
                record.set(entry.getKey(), entry.getValue());
            }
        }
        return record;
    }

    @SuppressWarnings("unchecked")
    @NotAction
    protected <T> T convertJsonModel(JSONObject jsonObject, Class<? extends Model<?>> clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
            Method setMethod = clazz.getMethod("set", String.class, Object.class);
            Table table = TableMapping.me().getTable(clazz);
            for (Map.Entry<String, Class<?>> entry : table.getColumnTypeMap().entrySet()) {
                String key = entry.getKey();
                String sValue = jsonObject.getString(key);
                Class<?> type = entry.getValue();
                Object value = typeConverter.convert(type, sValue);
                setMethod.invoke(object, key, value);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
                | IllegalArgumentException | InvocationTargetException | ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter!");
        }
        return (T) object;
    }

    @SuppressWarnings("unchecked")
    @NotAction
    protected <T> T getJsonModel(String name, Class<? extends Model<?>> clazz) {
        JSONObject jsonObject = getJsonObject(name);
        if (jsonObject == null) {
            return null;
        }
        Object object = convertJsonModel(jsonObject, clazz);
        return (T) object;
    }

    @SuppressWarnings("unchecked")
    @NotAction
    protected <T> T getJsonModel(Class<? extends Model<?>> clazz) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return null;
        }
        Object object = convertJsonModel(jsonObject, clazz);
        return (T) object;
    }

    @SuppressWarnings("unchecked")
    @NotAction
    protected <T> List<T> getJsonModelList(Class<? extends Model<?>> clazz) {
        JSONArray jsonArray = getJsonArray();
        if (jsonArray == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object iObject : jsonArray) {
            list.add((T) convertJsonModel((JSONObject) iObject, clazz));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @NotAction
    protected <T> List<T> getJsonModelList(String name, Class<? extends Model<?>> clazz) {
        JSONArray jsonArray = getJsonArray(name);
        if (jsonArray == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object iObject : jsonArray) {
            list.add((T) convertJsonModel((JSONObject) iObject, clazz));
        }
        return list;
    }

    @NotAction
    protected String getJsonPara(String name) {
        JSONObject object = getJsonObject();
        if ((object != null) && (!(object.isEmpty()))) {
            return object.getString(name);
        }
        return null;
    }

    @NotAction
    protected String getJsonPara(String name, String defaultValue) {
        String result = getJsonPara(name);
        return ((StrKit.isBlank(result)) ? defaultValue : result);
    }

    @NotAction
    protected Integer getJsonParaToInt(String name) {
        String result = getJsonPara(name);
        try {
            return (Integer) typeConverter.convert(Integer.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Integer!");
        }
    }

    @NotAction
    protected Integer getJsonParaToInt(String name, Integer defaultValue) {
        String result = getJsonPara(name);
        if (StrKit.isBlank(result)) {
            return defaultValue;
        }
        try {
            return (Integer) typeConverter.convert(Integer.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Integer!");
        }
    }

    @NotAction
    protected Long getJsonParaToLong(String name) {
        String result = getJsonPara(name);
        try {
            return (Long) typeConverter.convert(Long.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Long!");
        }
    }

    @NotAction
    protected Long getJsonParaToLong(String name, Long defaultValue) {
        String result = getJsonPara(name);
        if (StrKit.isBlank(result)) {
            return defaultValue;
        }
        try {
            return (Long) typeConverter.convert(Long.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Long!");
        }
    }

    @NotAction
    protected Boolean getJsonParaToBoolean(String name) {
        String result = getJsonPara(name);
        try {
            return (Boolean) typeConverter.convert(Boolean.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Boolean!");
        }
    }

    @NotAction
    protected Boolean getJsonParaToBoolean(String name, Boolean defaultValue) {
        String result = getJsonPara(name);
        if (StrKit.isBlank(result)) {
            return defaultValue;
        }
        try {
            return (Boolean) typeConverter.convert(Boolean.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Boolean!");
        }
    }

    @NotAction
    protected Date getJsonParaToDate(String name) {
        String result = getJsonPara(name);
        try {
            return (Date) typeConverter.convert(Date.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Date!");
        }
    }

    @NotAction
    protected Date getJsonParaToDate(String name, Date defaultValue) {
        String result = getJsonPara(name);
        if (StrKit.isBlank(result)) {
            return defaultValue;
        }
        try {
            return (Date) typeConverter.convert(Date.class, result);
        } catch (ConverterException e) {
            throw new ActionException(400, renderManager.getRenderFactory().getErrorRender(400),
                    "Can not parse the parameter " + name + " to Date!");
        }
    }

}