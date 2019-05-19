package com.tranquocanh.builder;

public class UserBuilder {

    private String userName;
    private String fullName;
    private String[] code;

    public UserBuilder(Builder builder) {
        this.userName = builder.userName;
        this.fullName = builder.fullName;
        this.code = builder.code;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String[] getCode() {
        return code;
    }

    public static class Builder {
        private String userName;
        private String fullName;
        private String[] code;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setCode(String[] code) {
            this.code = code;
            return this;
        }

        public UserBuilder build() {
            return new UserBuilder(this);
        }
    }
}
