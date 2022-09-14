package com.example.AppXml;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
public class JfsExceptionDto {
    public Date timestamp;
    public int status;
    public List<String> messages;
    public String path;
    public String method;
}
