package com.pam.pam_front.model;

import java.util.List;

public interface IResponse {
    void succeed();
    void succeed(List<Movie> movies);
    void failure();
    void setList(List list);
}
