package com.adapterj.example.vertx.spring.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * create table t_version (
 * id                   INTEGER                        not null,
 * id_source            INTEGER,
 * type                 INTEGER                        not null,
 * version              VARCHAR(31)                    not null,
 * year                 INTEGER                        not null,
 * serial               INTEGER                        not null,
 * insert_time          TIMESTAMP                      not null,
 * update_time          TIMESTAMP                      not null,
 * state                INTEGER                        not null,
 * primary key (id),
 * foreign key (id_source)
 *         references t_source (id)
 * );
 *
 * create unique index x_version_unique1 on t_version (
 *        id_source ASC, type ASC, year ASC, serial ASC
 * );
 *
 * create index x_version_version on t_version (
 *        version ASC
 * );
 *
 * create index x_version_state on t_version (
 *        state ASC
 * );
 * 
 * @author York/GuangYu DENG <york.deng@qq.com>
 *
 */
@ID(identity = "version", initials = "v")
@Entity
@Table(name = "t_version", uniqueConstraints = {
@UniqueConstraint(name="x_version_unique1", columnNames={"id_source", "type", "year", "serial"})}, indexes = {
@Index(name = "x_version_version", columnList = "version"),
@Index(name = "x_version_state", columnList = "state")})
public class Version implements Jsonable, Htmlable, Serializable {

    private static final long serialVersionUID = -6126939183193354845L;

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ID_SOURCE = "id_source";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_VERSION = "version";

    public static final String COLUMN_YEAR = "year";

    public static final String COLUMN_SERIAL = "serial";

    public static final String COLUMN_INSERT_TIME = "insert_time";

    public static final String COLUMN_UPDATE_TIME = "update_time";

    public static final String COLUMN_STATE = "state";

    public static final int TYPE_SOURCE = 0x01;

    public static final int TYPE_METADATE_OF_ARTICLE = 0x10;

    public static final int TYPE_METADATE_OF_ARTICLE_LIST = 0xa0;

    public static final int TYPE_METADATE_OF_SEARCH_RESULT_LIST = 0xf0;

    public static final int STATE_DISABLE = Integer.MIN_VALUE;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "id_source", referencedColumnName = "id")
    private Source source;
    
    @GetMethod(methodName = "getSource", returnType = "Source", returnId = "source")
    public final Source getSource() {
        return source;
    }

    @SetMethod(methodName = "setSource", parameterType = "Source", parameterId = "source")
    public final void setSource(Source source) {
        this.source = source;
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

    @Column(name = "version", nullable = false)
    private String version;

    @GetMethod(methodName = "getVersion", returnType = "String", returnId = "version")
    public String getVersion() {
    	return version != null ? version : (year != null && serial != null ? version(year, serial) : null);
    } // York/GuangYu DENG

    @SetMethod(methodName = "setVersion", parameterType = "String", parameterId = "version")
    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "year", nullable = false)
    private Integer year;

    @GetMethod(methodName = "getVersion", returnType = "int", returnId = "year")
    public final Integer getYear() {
        return year != null ? year : (version != null ? year(version) : null);
    }

    @SetMethod(methodName = "setYear", parameterType = "int", parameterId = "year")
    public final void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "serial", nullable = false)
    private Integer serial;

    @GetMethod(methodName = "getSerial", returnType = "int", returnId = "serial")
    public final Integer getSerial() {
    	return serial != null ? serial : (version != null ? serial(version) : null);
    }

    @SetMethod(methodName = "setSerial", parameterType = "int", parameterId = "serial")
    public final void setSerial(Integer serial) {
        this.serial = serial;
    }

    @Column(name = "insert_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    public final Date getInsertTime() {
        return insertTime;
    }

    public final void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public final Date getUpdateTime() {
        return updateTime;
    }

    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "state", nullable = false)
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer state) {
        this.state = state;
    }

    // to HTML string, JSON string, or just a plain string

    @Override
    public String toJSONString() {
        StringBuilder s = new StringBuilder().append('{');
        s.append("\"id\":\"").append(id);
        if (source != null) s.append("\", ").append("\"id_source\":\"").append(source != null ? source.getId() : -1);
        if (type != null) s.append("\", ").append("\"type\":\"").append(type);
        if (version != null) s.append("\", ").append("\"version\":\"").append(version);
        if (year != null) s.append("\", ").append("\"year\":\"").append(year);
        if (serial != null) s.append("\", ").append("\"serial\":\"").append(serial);
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
        if (id != null) return (Long.toString(id));
        else return toJSONString();
    }
    
    // protected methods
    
    protected static final NumberFormat format = new DecimalFormat("000"); // York/GuangYu DENG
    
    protected final String version(final Integer year, final Integer serial) {
    	return (new StringBuffer()).append('v').append(year).append('_').append(format.format(serial)).toString();
    } // York/GuangYu DENG
    
    protected final Integer year(final String version) {
    	if (version == null) return (null);
    	if (!version.startsWith("v")) return (null);
    	if (version.length() < 9) return (null); // Such as: v2019_001
    	try { return Integer.parseInt(version.substring(1, 5)); } catch (Throwable ignore) { };
    	return (null);
    } // York/GuangYu DENG
    
    protected final Integer serial(final String version) {
    	if (version == null) return (null);
    	if (!version.startsWith("v")) return (null);
    	if (version.length() < 9) return (null); // Such as: v2019_001
    	try { return Integer.parseInt(version.substring(6, 9)); } catch (Throwable ignore) { };
    	return (null);
    } // York/GuangYu DENG
}
