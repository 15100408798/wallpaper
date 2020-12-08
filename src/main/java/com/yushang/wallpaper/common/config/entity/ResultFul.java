package com.yushang.wallpaper.common.config.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class ResultFul implements Serializable {

    private static final long serialVersionUID = 865886327862350393L;
    private int status; // 响应状态，200 成功，400 请求无效， 500 服务器异常
    private String message; // 提示信息
    private Object rows;    // 响应报文
    private long total;      // 响应条数
    private static final String SUCCESS = "SUCCESS";    // 成功
    private static final String ERROR = "ERROR";        // 失败

    public ResultFul(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultFul(Object rows) {
        this(rows, 0L);
    }

    public ResultFul(Object rows, long total) {
        this(HttpStatus.SC_OK, SUCCESS, rows, total);
    }

    public ResultFul(int status, String message, Object rows, long total) {
        this.status = status;
        this.message = message;
        this.rows = rows;
        this.total = total;
    }


    /**
     * 响应失败
     *
     * @param message 提示信息
     * @return 响应报文
     */
    @NonNull
    public static ResultFul getErrorMessage(@NonNull String message) {
        Objects.requireNonNull(message);
        return new ResultFul(HttpStatus.SC_BAD_REQUEST, message);
    }


    /**
     * 响应成功
     *
     * @param rows 返回信息
     * @return 响应报文
     */
    @NonNull
    public static ResultFul getSuccessRows(@NonNull Object rows) {
        Objects.requireNonNull(rows);
        return new ResultFul(rows);
    }


    /**
     * 响应成功
     *
     * @param rows 返回信息
     * @return 响应报文
     */
    @NonNull
    public static ResultFul getSuccessList(@Nullable List<?> rows, @NonNull long total) {
        if (CollectionUtils.isEmpty(rows) || rows.get(0) == null) {
            rows = new ArrayList(0);
        }
        return new ResultFul(rows, total);
    }

    /**
     * 更新、添加后影响条数
     *
     * @param count 数量
     * @return 响应报文
     */
    @NonNull
    public static ResultFul getSuccessTotal(@NonNull int count) {
        ResultFul resultFul = new ResultFul();
        resultFul.setStatus(HttpStatus.SC_OK);
        resultFul.setMessage(SUCCESS);
        resultFul.setTotal(count);
        return resultFul;
    }

    public static Map<String, Object> getListMap(List rows, int total) {
        if (rows == null || rows.size() == 0 || rows.get(0) == null) {
            rows = new ArrayList();
        }
        HashMap<String, Object> param = new LinkedHashMap<>();
        param.put("status", 200);
        param.put("rows", rows);
        param.put("total", total);
        return param;
    }

    public static Map<String, Object> getStatusMap() {
        HashMap<String, Object> param = new LinkedHashMap<>();
        param.put("status", 200);
        return param;
    }

    public static Map<String, Object> getRowsMap(List list) {
        return null;
    }
}
