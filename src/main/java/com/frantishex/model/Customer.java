 package com.frantishex.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Transactional
public class Customer {

	public static final BigDecimal ZERO = new BigDecimal(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@JsonIgnore
	private BigDecimal discount;

	@JsonIgnore
	private String Tier;

	@JsonIgnore
	private BigDecimal turnover;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	@JsonIgnore
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getTier() {
		return Tier;
	}

	@JsonIgnore
	public void setTier(String tier) {
		Tier = tier;
	}

}
