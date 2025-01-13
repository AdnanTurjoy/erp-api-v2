package com.api.erp.utils;


public class ApiResponse<T> {

    private boolean success; // Indicates if the operation was successful
    private String message;  // Success or error message
    private T data;          // Generic data object
    private String timestamp; // Time of the response

    public ApiResponse() {
        this.timestamp = java.time.LocalDateTime.now().toString(); // Auto-set timestamp
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
