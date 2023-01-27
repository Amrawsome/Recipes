package com.example.recipes;

public class User {
    int _userID;
    String _userFullName;
    String _userName;
    String _userPassword;


    public User(){}

    public User(int _userID,String userFN,String userName, String userPassword){
        this._userID = _userID;
        this._userFullName = userFN;
        this._userName = userName;
        this._userPassword = userPassword;


    }

    public int get_userID() {
        return _userID;
    }

    public void set_userID(int _userID) {
        this._userID = _userID;
    }

    public String get_userFullName() {
        return _userFullName;
    }

    public void set_userFullName(String _userFirstName) {
        this._userFullName = _userFullName;
    }



    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_userPassword() {
        return _userPassword;
    }

    public void set_userPassword(String _userPassword) {
        this._userPassword = _userPassword;
    }


}
