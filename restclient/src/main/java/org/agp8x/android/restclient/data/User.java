package org.agp8x.android.restclient.data;

/**
 * Created by clemensk on 02.10.16.
 */
public class User implements RestObject {
    private final String _name;
    private final String _password;
    private String _token;

    public void setToken(String token) {
        _token = token;
    }

    public String getToken() {
        return _token;
    }

    public User(String name, String password) {
        _name = name;
        _password = password;
    }

    public User(String token) {
        _password = null;
        _name = null;
        _token = token;
    }

    public String getName() {
        return _name;
    }


    public String getPassword() {
        return _password;
    }
}
