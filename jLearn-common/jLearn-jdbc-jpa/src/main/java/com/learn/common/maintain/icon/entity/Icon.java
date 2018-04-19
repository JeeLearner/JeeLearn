package com.learn.common.maintain.icon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * 图标 实体类
 * @author JeeLearner
 * @date 2018年4月11日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "maintain_icon")
public class Icon extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3222024146941762749L;

	/**
     * 标识 前台使用时的名称
     */
	private String identity;
	/**
     * class名称
     */
	@Column(name = "css_class")
	private String cssClass;
	 
	//////和 class、css sprite 三者选一
    /**
     * 图片地址
     */
	@Column(name = "img_src")
	private String imgSrc;
	
	//////和 class、css sprite 三者选一
  /**
   * css 背景图 位置
   * 绝对地址  如http://a.com/a.png
   * 相对于上下文地址 如/a/a.png 不加上下文
   */
	@Column(name = "sprite_src")
	private String spriteSrc;
	
	/**
     * 宽度
     */
    private Integer width;
    /**
     * 高度
     */
    private Integer height;

    /**
     * 距离sprite图片左边多少
     */
	private int left;
	 /**
     * 距离sprite图片上边多少
     */
	private int top;
	
	/**
     * 额外添加的css样式
     */
	private String style = "";
	
	@Enumerated(EnumType.STRING)
	private IconType type;
	/**
     * 描述
     */
	private String description;
	
	
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getSpriteSrc() {
		return spriteSrc;
	}
	public void setSpriteSrc(String spriteSrc) {
		this.spriteSrc = spriteSrc;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public IconType getType() {
		return type;
	}
	public void setType(IconType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
