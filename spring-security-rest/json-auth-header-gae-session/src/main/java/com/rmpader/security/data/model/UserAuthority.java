package com.rmpader.security.data.model;

import com.rmpader.security.util.Authority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author RMPader
 */
@Entity
@Table(name = "user_authorities")
public class UserAuthority implements Serializable{

    @EmbeddedId
    private UserAuthoritiesId id;

    private UserAuthority(){}

    public UserAuthority(UserProfile userProfile, Authority authority) {
        this.id = new UserAuthoritiesId(userProfile.getUsername(), authority);
    }

    public UserAuthoritiesId getId() {
        return id;
    }

    @Embeddable
    public static class UserAuthoritiesId implements Serializable {

        private String username;
        private String authority;

        private UserAuthoritiesId(){}

        public UserAuthoritiesId(String username, Authority authority) {
            this.username = username;
            this.authority = authority.name();
        }

        public String getUsername() {
            return username;
        }

        public String getAuthority() {
            return authority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserAuthoritiesId that = (UserAuthoritiesId) o;
            return Objects.equals(getUsername(), that.getUsername()) &&
                    Objects.equals(getAuthority(), that.getAuthority());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUsername(), getAuthority());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
