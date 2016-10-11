package org.agp8x.android.restclient.data;

/**
 * Created by clemensk on 02.10.16.
 */
public class User implements RestObject {
    private final String username;
    private final String password;
    private String _token;

    public void setToken(String token) {
        _token = token;
    }

    public String getToken() {
        return _token;
    }

    public User(String name, String password) {
        username = name;
        this.password = password;
    }

    public User(String token) {
        password = null;
        username = null;
        _token = token;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }
}
