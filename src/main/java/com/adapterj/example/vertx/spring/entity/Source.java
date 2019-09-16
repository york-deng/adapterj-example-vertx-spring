package com.adapterj.example.vertx.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.adapterj.annotation.GetMethod;
import com.adapterj.annotation.ID;
import com.adapterj.annotation.SetMethod;

import com.adapterj.web.Htmlable;
import com.adapterj.web.Jsonable;

/**
 *
 * SQL:
 *
 * create table t_source (
 * id                   INTEGER                        not null,
 * name                 VARCHAR(255)                   not null,
 * abbr                 VARCHAR(255)                   not null,
 * logo                 VARCHAR(255),
 * domain               VARCHAR(255)                   not null,
 * www                  VARCHAR(255)                   not null,
 * description          VARCHAR(511),
 * type                 INTEGER                        not null,
 * country              CHAR(2),
 * language             CHAR(5),
 * specific             VARCHAR(511),
 * priority             INTEGER                        not null,
 * latency              INTEGER                        not null default 300,
 * label1               VARCHAR(255),
 * url1                 VARCHAR(255),
 * limit1               INTEGER                        default 0,
 * type1                INTEGER,
 * encoding1            VARCHAR(31),
 * label2               VARCHAR(255),
 * url2                 VARCHAR(255),
 * limit2               INTEGER                        default 0,
 * type2                INTEGER,
 * encoding2            VARCHAR(31),
 * label3               VARCHAR(255),
 * url3                 VARCHAR(255),
 * limit3               INTEGER                        default 0,
 * type3                INTEGER,
 * encoding3            VARCHAR(31),
 * label4               VARCHAR(255),
 * url4                 VARCHAR(255),
 * limit4               INTEGER                        default 0,
 * type4                INTEGER,
 * encoding4            VARCHAR(31),
 * label5               VARCHAR(255),
 * url5                 VARCHAR(255),
 * limit5               INTEGER                        default 0,
 * type5                INTEGER,
 * encoding5            VARCHAR(31),
 * label6               VARCHAR(255),
 * url6                 VARCHAR(255),
 * limit6               INTEGER                        default 0,
 * type6                INTEGER,
 * encoding6            VARCHAR(31),
 * label7               VARCHAR(255),
 * url7                 VARCHAR(255),
 * limit7               INTEGER                        default 0,
 * type7                INTEGER,
 * encoding7            VARCHAR(31),
 * label8               VARCHAR(255),
 * url8                 VARCHAR(255),
 * limit8               INTEGER                        default 0,
 * type8                INTEGER,
 * encoding8            VARCHAR(31),
 * label9               VARCHAR(255),
 * url9                 VARCHAR(255),
 * limit9               INTEGER                        default 0,
 * type9                INTEGER,
 * encoding9            VARCHAR(31),
 * version              VARCHAR(31)                    not null,
 * insert_time          TIMESTAMP                      not null,
 * update_time          TIMESTAMP                      not null,
 * state                INTEGER                        not null,
 * primary key (id)
 * );
 *
 * create unique index x_source_name on t_source (
 *        name ASC
 * );
 *
 * create unique index x_source_abbr on t_source (
 *        abbr ASC
 * );
 *
 * create unique index x_source_domain on t_source (
 *        domain ASC
 * );
 *
 * create unique index x_source_www on t_source (
 *        www ASC
 * );
 *
 * create index x_source_state on t_source (
 *        state ASC
 * );
 * 
 * @author York/GuangYu DENG <york.deng@qq.com>
 * 
 */
@ID(identity = "source", initials = "s")
@Entity
@Table(name = "t_source", uniqueConstraints = {
@UniqueConstraint(name="x_source_name", columnNames={"name"}),
@UniqueConstraint(name="x_source_abbr", columnNames={"abbr"}),
@UniqueConstraint(name="x_source_domain", columnNames={"domain"}),
@UniqueConstraint(name="x_source_www", columnNames={"www"})}, indexes = {
@Index(name = "x_source_state", columnList = "state")})
public class Source implements Jsonable, Htmlable, Serializable {

    private static final long serialVersionUID = -555782359639459538L;

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ABBR = "abbr";

    public static final String COLUMN_DOMAIN = "domain";

    public static final String COLUMN_WWW = "www";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_COUNTRY = "country";

    public static final String COLUMN_LANGUAGE = "language";

    public static final String COLUMN_PRIORITY = "priority";

    public static final String COLUMN_LATENCY = "latency";

    public static final String COLUMN_LABEL1 = "label1";

    public static final String COLUMN_URL1 = "url1";

    public static final String COLUMN_LIMIT1 = "limit1";

    public static final String COLUMN_TYPE1 = "type1";

    public static final String COLUMN_VERSION = "version";

    public static final String COLUMN_STATE = "state";

    public static final int TYPE_OFFICIAL_SITE = 0x00;

    public static final int TYPE_OFFICIAL_SITE_HOMEPAGE = 0x01;

    public static final int TYPE_OFFICIAL_SITE_LIST_PAGES = 0x02;

    public static final int TYPE_OFFICIAL_SITE_RSS = 0x08;

    public static final int TYPE_THIRD_PARTY_PLATFORM = 0x10;

    public static final int TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE = 0x11;

    public static final int TYPE_THIRD_PARTY_LIST_PAGES = 0x12;

    public static final int TYPE_THIRD_PARTY_RSS = 0x18;

    public static final int TYPE_SEARCH_ENGINE = 0xF0;

    public static final int TYPE_SEARCH_ENGINE_HOMEPAGE = 0xF1;

    public static final int TYPE_SEARCH_ENGINE_RESULTS_PAGES = 0xF2;

    public static final int STATE_UNPUBLISHED = Integer.MIN_VALUE; // Server Side State

    public static final int STATE_PUBLISHED = 0; // Server Side State

    public static final int STATE_DISABLE = Integer.MIN_VALUE; // Client Side State, STATE_DISABLE == STATE_UNPUBLISHED

    public static final int STATE_SUBSCRIBED = 0; // Client Side State, STATE_SUBSCRIBED == STATE_PUBLISHED

    public static final int STATE_UNSUBSCRIBED_URL1 = 0xF001; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL2 = 0xF002; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL3 = 0xF004; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL4 = 0xF008; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL5 = 0xF010; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL6 = 0xF020; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL7 = 0xF040; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL8 = 0xF080; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL9 = 0xF100; // Client Side State

    public static final int STATE_UNSUBSCRIBED = 0xF1FF; // Client Side State

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @GetMethod(methodName = "getId", returnType = "long", returnId = "id")
    public final Long getId() {
        return id;
    }

    @SetMethod(methodName = "setId", parameterType = "long", parameterId = "id")
    public final void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @GetMethod(methodName = "getName", returnType = "String", returnId = "name")
    public final String getName() {
        return name;
    }

    @SetMethod(methodName = "setName", parameterType = "String", parameterId = "name")
    public final void setName(String name) {
        this.name = name;
    }

    @Column(name = "abbr", nullable = false)
    private String abbr;

    @GetMethod(methodName = "getAbbr", returnType = "String", returnId = "abbr")
    public final String getAbbr() {
        return abbr;
    }

    @SetMethod(methodName = "setAbbr", parameterType = "String", parameterId = "abbr")
    public final void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Column(name = "logo", length = 255)
    private String logo;

    @GetMethod(methodName = "getLogo", returnType = "String", returnId = "logo")
    public final String getLogo() {
        return logo;
    }

    @SetMethod(methodName = "setLogo", parameterType = "String", parameterId = "logo")
    public final void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "domain", nullable = false)
    private String domain;

    @GetMethod(methodName = "getDomain", returnType = "String", returnId = "domain")
    public final String getDomain() {
        return domain;
    }

    @SetMethod(methodName = "setDomain", parameterType = "String", parameterId = "domain")
    public final void setDomain(String domain) {
        this.domain = domain;
    }

    @Column(name = "www", nullable = false)
    private String www;

    @GetMethod(methodName = "getWWW", returnType = "String", returnId = "www")
    public final String getWWW() {
        return www;
    }

    @SetMethod(methodName = "setWWW", parameterType = "String", parameterId = "www")
    public final void setWWW(String www) {
        this.www = www;
    }

    @Column(name = "description", length = 511)
    private String description;

    @GetMethod(methodName = "getDescription", returnType = "String", returnId = "description")
    public String getDescription() {
        return description;
    }

    @SetMethod(methodName = "setDescription", parameterType = "String", parameterId = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "type", nullable = false)
    private Integer type;
    
    @GetMethod(methodName = "getType", returnType = "int", returnId = "type")
    public final Integer getType() {
            return type;
    }
    
    @SetMethod(methodName = "setType", parameterType = "int", parameterId = "type")
    public final void setType(Integer type) {
            this.type = type;
    }

    @Column(name = "country")
    private String country;

    @GetMethod(methodName = "getCountry", returnType = "String", returnId = "country")
    public String getCountry() {
        return country;
    }

    @SetMethod(methodName = "setCountry", parameterType = "String", parameterId = "country")
    public void setCountry(final String country) {
        this.country = country;
    }

    @Column(name = "language")
    private String language;

    @GetMethod(methodName = "getLanguage", returnType = "String", returnId = "language")
    public String getLanguage() {
        return language;
    }

    @SetMethod(methodName = "setLanguage", parameterType = "String", parameterId = "language")
    public void setLanguage(final String language) {
        this.language = language;
    }

    @Column(name = "specific", length = 511)
    private String specific;

    @GetMethod(methodName = "getSpecific", returnType = "String", returnId = "specific")
    public String getSpecific() {
        return specific;
    }

    @SetMethod(methodName = "setSpecific", parameterType = "String", parameterId = "specific")
    public void setSpecific(String specific) {
        this.specific = specific;
    }

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @GetMethod(methodName = "getPriority", returnType = "int", returnId = "priority")
    public final Integer getPriority() {
        return priority;
    }

    @SetMethod(methodName = "setPriority", parameterType = "int", parameterId = "priority")
    public final void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Column(name = "latency", nullable = false)
    private Integer latency = 300;

    @GetMethod(methodName = "getLatency", returnType = "int", returnId = "latency")
    public final Integer getLatency() {
        return latency;
    }

    @SetMethod(methodName = "setLatency", parameterType = "int", parameterId = "latency")
    public final void setLatency(Integer latency) {
        this.latency = latency;
    }

    @Column(name = "label1")
    private String label1;

    @GetMethod(methodName = "getLabel1", returnType = "String", returnId = "label1")
    public String getLabel1() {
        return label1;
    }

    @SetMethod(methodName = "setLabel1", parameterType = "String", parameterId = "label1")
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    @Column(name = "url1", nullable = false, length = 255)
    private String url1;

    @GetMethod(methodName = "getURL1", returnType = "String", returnId = "url1")
    public String getURL1() {
        return url1;
    }

    @SetMethod(methodName = "setURL1", parameterType = "String", parameterId = "url1")
    public void setURL1(String url1) {
        this.url1 = url1;
    }

    @Column(name = "limit1")
    private Integer limit1 = 0;

    @GetMethod(methodName = "getLimit1", returnType = "int", returnId = "limit1")
    public final Integer getLimit1() {
        return limit1;
    }

    @SetMethod(methodName = "setLimit1", parameterType = "int", parameterId = "limit1")
    public final void setLimit1(final Integer limit1) {
        this.limit1 = limit1;
    }

    @Column(name = "type1", nullable = false)
    private Integer type1;

    @GetMethod(methodName = "getType1", returnType = "int", returnId = "type1")
    public final Integer getType1() {
        return type1;
    }

    @SetMethod(methodName = "setType1", parameterType = "int", parameterId = "type1")
    public final void setType1(Integer type1) {
        this.type1 = type1;
    }

    @Column(name = "encoding1", nullable = false)
    private String encoding1;

    @GetMethod(methodName = "getEncoding1", returnType = "String", returnId = "encoding1")
    public String getEncoding1() {
        return encoding1;
    }

    @SetMethod(methodName = "setEncoding1", parameterType = "String", parameterId = "encoding1")
    public void setEncoding1(String encoding1) {
        this.encoding1 = encoding1;
    }

    @Column(name = "label2")
    private String label2;

    @GetMethod(methodName = "getLabel2", returnType = "String", returnId = "label2")
    public String getLabel2() {
        return label2;
    }

    @SetMethod(methodName = "setLabel2", parameterType = "String", parameterId = "label2")
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    @Column(name = "url2", length = 255)
    private String url2;

    @GetMethod(methodName = "getURL2", returnType = "String", returnId = "url2")
    public String getURL2() {
        return url2;
    }

    @SetMethod(methodName = "setURL2", parameterType = "String", parameterId = "url2")
    public void setURL2(String url2) {
        this.url2 = url2;
    }

    @Column(name = "limit2")
    private Integer limit2 = 0;
    
    @GetMethod(methodName = "getLimit2", returnType = "int", returnId = "limit2")
    public final Integer getLimit2() {
        return limit2;
    }

    @SetMethod(methodName = "setLimit2", parameterType = "int", parameterId = "limit2")
    public final void setLimit2(final Integer limit2) {
        this.limit2 = limit2;
    }

    @Column(name = "type2")
    private Integer type2;

    @GetMethod(methodName = "getType2", returnType = "int", returnId = "type2")
    public final Integer getType2() {
        return type2;
    }

    @SetMethod(methodName = "setType2", parameterType = "int", parameterId = "type2")
    public final void setType2(Integer type2) {
        this.type2 = type2;
    }

    @Column(name = "encoding2")
    private String encoding2;

    @GetMethod(methodName = "getEncoding2", returnType = "String", returnId = "encoding2")
    public String getEncoding2() {
        return encoding2;
    }

    @SetMethod(methodName = "setEncoding2", parameterType = "String", parameterId = "encoding2")
    public void setEncoding2(String encoding2) {
        this.encoding2 = encoding2;
    }

    @Column(name = "label3")
    private String label3;

    @GetMethod(methodName = "getLabel3", returnType = "String", returnId = "label3")
    public String getLabel3() {
        return label3;
    }

    @SetMethod(methodName = "setLabel3", parameterType = "String", parameterId = "label3")
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @Column(name = "url3", length = 255)
    private String url3;

    @GetMethod(methodName = "getURL3", returnType = "String", returnId = "url3")
    public String getURL3() {
        return url3;
    }

    @SetMethod(methodName = "setURL3", parameterType = "String", parameterId = "url3")
    public void setURL3(String url3) {
        this.url3 = url3;
    }

    @Column(name = "limit3")
    private Integer limit3 = 0;

    @GetMethod(methodName = "getLimit3", returnType = "int", returnId = "limit3")
    public final Integer getLimit3() {
        return limit3;
    }

    @SetMethod(methodName = "setLimit3", parameterType = "int", parameterId = "limit3")
    public final void setLimit3(final Integer limit3) {
        this.limit3 = limit3;
    }

    @Column(name = "type3")
    private Integer type3;

    @GetMethod(methodName = "getType3", returnType = "int", returnId = "type3")
    public final Integer getType3() {
        return type3;
    }

    @SetMethod(methodName = "setType3", parameterType = "int", parameterId = "type3")
    public final void setType3(Integer type3) {
        this.type3 = type3;
    }

    @Column(name = "encoding3")
    private String encoding3;

    @GetMethod(methodName = "getEncoding3", returnType = "String", returnId = "encoding3")
    public String getEncoding3() {
        return encoding3;
    }

    @SetMethod(methodName = "setEncoding3", parameterType = "String", parameterId = "encoding3")
    public void setEncoding3(String encoding3) {
        this.encoding3 = encoding3;
    }

    @Column(name = "label4")
    private String label4;

    @GetMethod(methodName = "getLabel4", returnType = "String", returnId = "label4")
    public String getLabel4() {
        return label4;
    }

    @SetMethod(methodName = "getLabel4", parameterType = "String", parameterId = "label4")
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    @Column(name = "url4", length = 255)
    private String url4;

    @GetMethod(methodName = "getURL4", returnType = "String", returnId = "url4")
    public String getURL4() {
        return url4;
    }

    @SetMethod(methodName = "setURL4", parameterType = "String", parameterId = "url4")
    public void setURL4(String url4) {
        this.url4 = url4;
    }

    @Column(name = "limit4")
    private Integer limit4 = 0;

    @GetMethod(methodName = "getLimit4", returnType = "int", returnId = "limit4")
    public final Integer getLimit4() {
        return limit4;
    }

    @SetMethod(methodName = "setLimit4", parameterType = "int", parameterId = "limit4")
    public final void setLimit4(final Integer limit4) {
        this.limit4 = limit4;
    }

    @Column(name = "type4")
    private Integer type4;

    @GetMethod(methodName = "getType4", returnType = "int", returnId = "type4")
    public final Integer getType4() {
        return type4;
    }

    @SetMethod(methodName = "setType4", parameterType = "int", parameterId = "type4")
    public final void setType4(Integer type4) {
        this.type4 = type4;
    }

    @Column(name = "encoding4")
    private String encoding4;

    @GetMethod(methodName = "getEncoding4", returnType = "String", returnId = "encoding4")
    public String getEncoding4() {
        return encoding4;
    }

    @SetMethod(methodName = "setEncoding4", parameterType = "String", parameterId = "encoding4")
    public void setEncoding4(String encoding4) {
        this.encoding4 = encoding4;
    }

    @Column(name = "label5")
    private String label5;

    @GetMethod(methodName = "getLabel5", returnType = "String", returnId = "label5")
    public String getLabel5() {
        return label5;
    }

    @SetMethod(methodName = "getLabel5", parameterType = "String", parameterId = "label5")
    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    @Column(name = "url5", length = 255)
    private String url5;

    @GetMethod(methodName = "getURL5", returnType = "String", returnId = "url5")
    public String getURL5() {
        return url5;
    }

    @SetMethod(methodName = "setURL5", parameterType = "String", parameterId = "url5")
    public void setURL5(String url5) {
        this.url5 = url5;
    }

    @Column(name = "limit5")
    private Integer limit5 = 0;

    @GetMethod(methodName = "getLimit5", returnType = "int", returnId = "limit5")
    public final Integer getLimit5() {
        return limit5;
    }

    @SetMethod(methodName = "setLimit5", parameterType = "int", parameterId = "limit5")
    public final void setLimit5(final Integer limit5) {
        this.limit5 = limit5;
    }

    @Column(name = "type5")
    private Integer type5;

    @GetMethod(methodName = "getType5", returnType = "int", returnId = "type5")
    public final Integer getType5() {
        return type5;
    }

    @SetMethod(methodName = "setType5", parameterType = "int", parameterId = "type5")
    public final void setType5(Integer type5) {
        this.type5 = type5;
    }

    @Column(name = "encoding5")
    private String encoding5;

    @GetMethod(methodName = "getEncoding5", returnType = "String", returnId = "encoding5")
    public String getEncoding5() {
        return encoding5;
    }

    @SetMethod(methodName = "setEncoding5", parameterType = "String", parameterId = "encoding5")
    public void setEncoding5(String encoding5) {
        this.encoding5 = encoding5;
    }

    @Column(name = "label6")
    private String label6;

    @GetMethod(methodName = "getLabel6", returnType = "String", returnId = "label6")
    public String getLabel6() {
        return label6;
    }

    @SetMethod(methodName = "getLabel6", parameterType = "String", parameterId = "label6")
    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    @Column(name = "url6", length = 255)
    private String url6;

    @GetMethod(methodName = "getURL6", returnType = "String", returnId = "url6")
    public String getURL6() {
        return url6;
    }

    @SetMethod(methodName = "setURL6", parameterType = "String", parameterId = "url6")
    public void setURL6(String url6) {
        this.url6 = url6;
    }

    @Column(name = "limit6")
    private Integer limit6 = 0;

    @GetMethod(methodName = "getLimit6", returnType = "int", returnId = "limit6")
    public final Integer getLimit6() {
        return limit6;
    }

    @SetMethod(methodName = "setLimit6", parameterType = "int", parameterId = "limit6")
    public final void setLimit6(final Integer limit6) {
        this.limit6 = limit6;
    }

    @Column(name = "type6")
    private Integer type6;

    @GetMethod(methodName = "getType6", returnType = "int", returnId = "type6")
    public final Integer getType6() {
        return type6;
    }

    @SetMethod(methodName = "setType6", parameterType = "int", parameterId = "type6")
    public final void setType6(Integer type6) {
        this.type6 = type6;
    }

    @Column(name = "encoding6")
    private String encoding6;

    @GetMethod(methodName = "getEncoding6", returnType = "String", returnId = "encoding6")
    public String getEncoding6() {
        return encoding6;
    }

    @SetMethod(methodName = "setEncoding6", parameterType = "String", parameterId = "encoding6")
    public void setEncoding6(String encoding6) {
        this.encoding6 = encoding6;
    }

    @Column(name = "label7")
    private String label7;

    @GetMethod(methodName = "getLabel7", returnType = "String", returnId = "label7")
    public String getLabel7() {
        return label7;
    }

    @SetMethod(methodName = "getLabel7", parameterType = "String", parameterId = "label7")
    public void setLabel7(String label7) {
        this.label7 = label7;
    }

    @Column(name = "url7", length = 255)
    private String url7;

    @GetMethod(methodName = "getURL7", returnType = "String", returnId = "url7")
    public String getURL7() {
        return url7;
    }

    @SetMethod(methodName = "setURL7", parameterType = "String", parameterId = "url7")
    public void setURL7(String url7) {
        this.url7 = url7;
    }

    @Column(name = "limit7")
    private Integer limit7 = 0;

    @GetMethod(methodName = "getLimit7", returnType = "int", returnId = "limit7")
    public final Integer getLimit7() {
        return limit7;
    }

    @SetMethod(methodName = "setLimit7", parameterType = "int", parameterId = "limit7")
    public final void setLimit7(final Integer limit7) {
        this.limit7 = limit7;
    }

    @Column(name = "type7")
    private Integer type7;

    @GetMethod(methodName = "getType7", returnType = "int", returnId = "type7")
    public final Integer getType7() {
        return type7;
    }

    @SetMethod(methodName = "setType7", parameterType = "int", parameterId = "type7")
    public final void setType7(Integer type7) {
        this.type7 = type7;
    }

    @Column(name = "encoding7")
    private String encoding7;

    @GetMethod(methodName = "getEncoding7", returnType = "String", returnId = "encoding7")
    public String getEncoding7() {
        return encoding7;
    }

    @SetMethod(methodName = "setEncoding7", parameterType = "String", parameterId = "encoding7")
    public void setEncoding7(String encoding7) {
        this.encoding7 = encoding7;
    }

    @Column(name = "label8")
    private String label8;

    @GetMethod(methodName = "getLabel8", returnType = "String", returnId = "label8")
    public String getLabel8() {
        return label8;
    }

    @SetMethod(methodName = "getLabel8", parameterType = "String", parameterId = "label8")
    public void setLabel8(String label8) {
        this.label8 = label8;
    }

    @Column(name = "url8", length = 255)
    private String url8;

    @GetMethod(methodName = "getURL8", returnType = "String", returnId = "url8")
    public String getURL8() {
        return url8;
    }

    @SetMethod(methodName = "setURL8", parameterType = "String", parameterId = "url8")
    public void setURL8(String url8) {
        this.url8 = url8;
    }

    @Column(name = "limit8")
    private Integer limit8 = 0;

    @GetMethod(methodName = "getLimit8", returnType = "int", returnId = "limit8")
    public final Integer getLimit8() {
        return limit8;
    }

    @SetMethod(methodName = "setLimit8", parameterType = "int", parameterId = "limit8")
    public final void setLimit8(final Integer limit8) {
        this.limit8 = limit8;
    }

    @Column(name = "type8")
    private Integer type8;

    @GetMethod(methodName = "getType8", returnType = "int", returnId = "type8")
    public final Integer getType8() {
        return type8;
    }

    @SetMethod(methodName = "setType8", parameterType = "int", parameterId = "type8")
    public final void setType8(Integer type8) {
        this.type8 = type8;
    }

    @Column(name = "encoding8")
    private String encoding8;

    @GetMethod(methodName = "getEncoding8", returnType = "String", returnId = "encoding8")
    public String getEncoding8() {
        return encoding8;
    }

    @SetMethod(methodName = "setEncoding8", parameterType = "String", parameterId = "encoding8")
    public void setEncoding8(String encoding8) {
        this.encoding8 = encoding8;
    }

    @Column(name = "label9")
    private String label9;

    @GetMethod(methodName = "getLabel9", returnType = "String", returnId = "label9")
    public String getLabel9() {
        return label9;
    }

    @SetMethod(methodName = "getLabel9", parameterType = "String", parameterId = "label9")
    public void setLabel9(String label9) {
        this.label9 = label9;
    }

    @Column(name = "url9", length = 255)
    private String url9;

    @GetMethod(methodName = "getURL9", returnType = "String", returnId = "url9")
    public String getURL9() {
        return url9;
    }

    @SetMethod(methodName = "setURL9", parameterType = "String", parameterId = "url9")
    public void setURL9(String url9) {
        this.url9 = url9;
    }

    @Column(name = "limit9")
    private Integer limit9 = 0;

    @GetMethod(methodName = "getLimit9", returnType = "int", returnId = "limit9")
    public final Integer getLimit9() {
        return limit9;
    }

    @SetMethod(methodName = "setLimit9", parameterType = "int", parameterId = "limit9")
    public final void setLimit9(final Integer limit9) {
        this.limit9 = limit9;
    }

    @Column(name = "type9")
    private Integer type9;

    @GetMethod(methodName = "getType9", returnType = "int", returnId = "type9")
    public final Integer getType9() {
        return type9;
    }

    @SetMethod(methodName = "setType9", parameterType = "int", parameterId = "type9")
    public final void setType9(Integer type9) {
        this.type9 = type9;
    }

    @Column(name = "encoding9")
    private String encoding9;

    @GetMethod(methodName = "getEncoding9", returnType = "String", returnId = "encoding9")
    public String getEncoding9() {
        return encoding9;
    }

    @SetMethod(methodName = "setEncoding9", parameterType = "String", parameterId = "encoding9")
    public void setEncoding9(String encoding9) {
        this.encoding9 = encoding9;
    }

    @Column(name = "version", nullable = false)
    private String version;

    @GetMethod(methodName = "getVersion", returnType = "String", returnId = "version")
    public String getVersion() {
        return version;
    }

    @SetMethod(methodName = "setVersion", parameterType = "String", parameterId = "version")
    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "insert_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    @GetMethod(methodName = "getInsertTime", returnType = "Date", returnId = "insertTime")
    public final Date getInsertTime() {
        return insertTime;
    }

    @SetMethod(methodName = "setInsertTime", parameterType = "Date", parameterId = "insertTime")
    public final void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @GetMethod(methodName = "getUpdateTime", returnType = "Date", returnId = "updateTime")
    public final Date getUpdateTime() {
        return updateTime;
    }

    @SetMethod(methodName = "setUpdateTime", parameterType = "Date", parameterId = "updateTime")
    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "state", nullable = false)
    private Integer state;

    @GetMethod(methodName = "getState", returnType = "int", returnId = "state", format = "#")
    public final Integer getState() {
        return state;
    }

    @SetMethod(methodName = "setState", parameterType = "int", parameterId = "state")
    public final void setState(Integer state) {
        this.state = state;
    }

    // Build select options for type
    
    // EN
    private static final Map<Integer, String> enTypes = new LinkedHashMap<Integer, String>();
    
    // zh-CN
    private static final Map<Integer, String> cnTypes = new LinkedHashMap<Integer, String>();

    /**
     * 
     * @return
     */
    public static Map<Integer, String> getTypes() {
    	return getTypes(Locale.ENGLISH);
    }
    
    /**
     * 
     * @param locale
     * @return
     */
    public static Map<Integer, String> getTypes(Locale locale) {
    	if (Locale.CHINESE.equals(locale)) {
	    	if (cnTypes.isEmpty()) {
	    		cnTypes.put(TYPE_OFFICIAL_SITE, "0x00 Official Website");
	    		cnTypes.put(TYPE_THIRD_PARTY_PLATFORM, "0x10 Third-Party Platform");
	    		cnTypes.put(TYPE_SEARCH_ENGINE, "0xf0 Search Engine");
	    	}
    		return cnTypes;
    	} else {
	    	if (enTypes.isEmpty()) {
	    		enTypes.put(TYPE_OFFICIAL_SITE, "0x00 Official Website");
	    		enTypes.put(TYPE_THIRD_PARTY_PLATFORM, "0x10 Third-Party Platform");
	    		enTypes.put(TYPE_SEARCH_ENGINE, "0xf0 Search Engine");
	    	}
	        return enTypes;
    	}
    }
    
    // Build select options for type1, type2, ..., type9 
    
    // EN
    private static final Map<Integer, String> enSubtypes = new LinkedHashMap<Integer, String>();
    
    // zh-CN
    private static final Map<Integer, String> cnSubtypes = new LinkedHashMap<Integer, String>();

    /**
     * 
     * @return
     */
    public static Map<Integer, String> getSubtypes() {
    	return getSubtypes(Locale.ENGLISH);
    }
    
    /**
     * 
     * @param locale
     * @return
     */
    public static Map<Integer, String> getSubtypes(Locale locale) {
    	if (Locale.CHINESE.equals(locale)) {
	    	if (cnSubtypes.isEmpty()) {
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_HOMEPAGE, "0x01 Official Website Homepage");
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_LIST_PAGES, "0x02 Official Website List Pages");
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_RSS, "0x08 Official Website RSS");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE, "0x11 Third-Party Platform Homepage");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_LIST_PAGES, "0x12 Third-Party Platform List Pages");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_RSS, "0x18 Third-Party Platform RSS");
	    		cnSubtypes.put(TYPE_SEARCH_ENGINE_HOMEPAGE, "0xf1 Search Engine Homepage");
	    		cnSubtypes.put(TYPE_SEARCH_ENGINE_RESULTS_PAGES, "0xf2 Search Engine Results Pages");
	    	}
    		return cnSubtypes;
    	} else {
	    	if (enSubtypes.isEmpty()) {
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_HOMEPAGE, "0x01 Official Website Homepage");
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_LIST_PAGES, "0x02 Official Website List Pages");
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_RSS, "0x08 Official Website RSS");
	    		enSubtypes.put(TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE, "0x11 Third-Party Platform Homepage");
	    		enSubtypes.put(TYPE_THIRD_PARTY_LIST_PAGES, "0x12 Third-Party Platform List Pages");
	    		enSubtypes.put(TYPE_THIRD_PARTY_RSS, "0x18 Third-Party Platform RSS");
	    		enSubtypes.put(TYPE_SEARCH_ENGINE_HOMEPAGE, "0xf1 Search Engine Homepage");
	    		enSubtypes.put(TYPE_SEARCH_ENGINE_RESULTS_PAGES, "0xf2 Search Engine Results Pages");
	    	}
	        return enSubtypes;
    	}
    }
    
    // to HTML string, JSON string, or just a plain string
    
    @Override
    public String toJSONString() {
        final StringBuilder s = new StringBuilder().append('{');
        s.append("\"id\":\"").append(id);
        if (name != null) s.append("\", ").append("\"name\":\"").append(name);
        if (abbr != null) s.append("\", ").append("\"abbr\":\"").append(abbr);
        if (logo != null) s.append("\", ").append("\"logo\":\"").append(logo);
        if (domain != null) s.append("\", ").append("\"domain\":\"").append(domain);
        if (www != null) s.append("\", ").append("\"www\":\"").append(www);
        if (description != null) s.append("\", ").append("\"description\":\"").append(description);
        if (type != null) s.append("\", ").append("\"type\":\"").append(type);
        if (country != null) s.append("\", ").append("\"country\":\"").append(country);
        if (language != null) s.append("\", ").append("\"language\":\"").append(language);
        if (specific != null) s.append("\", ").append("\"specific\":\"").append(specific);
        if (priority != null) s.append("\", ").append("\"priority\":\"").append(priority);
        if (latency != null) s.append("\", ").append("\"latency\":\"").append(latency);
        if (label1 != null) s.append("\", ").append("\"label1\":\"").append(label1);
        if (url1 != null) s.append("\", ").append("\"url1\":\"").append(url1);
        if (limit1 != null) s.append("\", ").append("\"limit1\":\"").append(limit1);
        if (type1 != null) s.append("\", ").append("\"type1\":\"").append(type1);
        if (encoding1 != null) s.append("\", ").append("\"encoding1\":\"").append(encoding1);
        if (label2 != null) s.append("\", ").append("\"label2\":\"").append(label2);
        if (url2 != null) s.append("\", ").append("\"url2\":\"").append(url2);
        if (limit2 != null) s.append("\", ").append("\"limit2\":\"").append(limit2);
        if (type2 != null) s.append("\", ").append("\"type2\":\"").append(type2);
        if (encoding2 != null) s.append("\", ").append("\"encoding2\":\"").append(encoding2);
        if (label3 != null) s.append("\", ").append("\"label3\":\"").append(label3);
        if (url3 != null) s.append("\", ").append("\"url3\":\"").append(url3);
        if (limit3 != null) s.append("\", ").append("\"limit3\":\"").append(limit3);
        if (type3 != null) s.append("\", ").append("\"type3\":\"").append(type3);
        if (encoding3 != null) s.append("\", ").append("\"encoding3\":\"").append(encoding3);
        if (label4 != null) s.append("\", ").append("\"label4\":\"").append(label4);
        if (url4 != null) s.append("\", ").append("\"url4\":\"").append(url4);
        if (limit4 != null) s.append("\", ").append("\"limit4\":\"").append(limit4);
        if (type4 != null) s.append("\", ").append("\"type4\":\"").append(type4);
        if (encoding4 != null) s.append("\", ").append("\"encoding4\":\"").append(encoding4);
        if (label5 != null) s.append("\", ").append("\"label5\":\"").append(label5);
        if (url5 != null) s.append("\", ").append("\"url5\":\"").append(url5);
        if (limit5 != null) s.append("\", ").append("\"limit5\":\"").append(limit5);
        if (type5 != null) s.append("\", ").append("\"type5\":\"").append(type5);
        if (encoding5 != null) s.append("\", ").append("\"encoding5\":\"").append(encoding5);
        if (label6 != null) s.append("\", ").append("\"label6\":\"").append(label6);
        if (url6 != null) s.append("\", ").append("\"url6\":\"").append(url6);
        if (limit6 != null) s.append("\", ").append("\"limit6\":\"").append(limit6);
        if (type6 != null) s.append("\", ").append("\"type6\":\"").append(type6);
        if (encoding6 != null) s.append("\", ").append("\"encoding6\":\"").append(encoding6);
        if (label7 != null) s.append("\", ").append("\"label7\":\"").append(label7);
        if (url7 != null) s.append("\", ").append("\"url7\":\"").append(url7);
        if (limit7 != null) s.append("\", ").append("\"limit7\":\"").append(limit7);
        if (type7 != null) s.append("\", ").append("\"type7\":\"").append(type7);
        if (encoding7 != null) s.append("\", ").append("\"encoding7\":\"").append(encoding7);
        if (label8 != null) s.append("\", ").append("\"label8\":\"").append(label8);
        if (url8 != null) s.append("\", ").append("\"url8\":\"").append(url8);
        if (limit8 != null) s.append("\", ").append("\"limit8\":\"").append(limit8);
        if (type8 != null) s.append("\", ").append("\"type8\":\"").append(type8);
        if (encoding8 != null) s.append("\", ").append("\"encoding8\":\"").append(encoding8);
        if (label9 != null) s.append("\", ").append("\"label9\":\"").append(label9);
        if (url9 != null) s.append("\", ").append("\"url9\":\"").append(url9);
        if (limit9 != null) s.append("\", ").append("\"limit9\":\"").append(limit9);
        if (type9 != null) s.append("\", ").append("\"type9\":\"").append(type9);
        if (encoding9 != null) s.append("\", ").append("\"encoding9\":\"").append(encoding9);
        if (version != null) s.append("\", ").append("\"version\":\"").append(version);
        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        if (insertTime != null) s.append("\", ").append("\"insert_time\":\"").append((insertTime == null) ? null : formatter.format(insertTime));
        if (updateTime != null) s.append("\", ").append("\"update_time\":\"").append((updateTime == null) ? null : formatter.format(updateTime));
        if (state != null) s.append("\", ").append("\"state\":\"").append(state);
        s.append("\"}");
        return s.toString();
    }
    
    @Override
    public String toHTMLString() {
    	final StringBuilder s = new StringBuilder();
    	s.append("<ul>").append("<li>");
    	s.append(Source.class.getName());
    	s.append("<ul>").append("<li>");
    	s.append(toJSONString());
    	s.append("</li>").append("</ul>");
    	s.append("</li>").append("</ul>");
    	return s.toString();
    }
    
    @Override
    public String toString() {
    	if (name != null) return (name);
    	else if (domain != null) return (domain);
    	else return toJSONString();
    }
}
