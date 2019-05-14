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

	private String email;

	@JsonIgnore
	private BigDecimal globalDiscount;
	@JsonIgnore
	private BigDecimal specificDiscount;
	@JsonIgnore
	private BigDecimal tierDiscount;

	@JsonIgnore
	private String Tier;

	@JsonIgnore
	private BigDecimal pointsForDiscount;

	@JsonIgnore
	private BigDecimal turnover;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getPointsForDiscount() {
		return pointsForDiscount;
	}

	public void setPointsForDiscount(BigDecimal pointsForDiscount) {
		this.pointsForDiscount = pointsForDiscount;
	}

	public BigDecimal getGlobalDiscount() {
		return globalDiscount;
	}

	public void setGlobalDiscount(BigDecimal globalDiscount) {
		this.globalDiscount = globalDiscount;
	}

	public BigDecimal getSpecificDiscount() {
		return specificDiscount;
	}

	public void setSpecificDiscount(BigDecimal specificDiscount) {
		this.specificDiscount = specificDiscount;
	}

	public BigDecimal getTierDiscount() {
		return tierDiscount;
	}

	public void setTierDiscount(BigDecimal tierDiscount) {
		this.tierDiscount = tierDiscount;
	}

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

	public String getTier() {
		return Tier;
	}

	@JsonIgnore
	public void setTier(String tier) {
		Tier = tier;
	}

}
