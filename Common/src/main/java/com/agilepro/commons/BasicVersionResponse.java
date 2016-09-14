package com.agilepro.commons;

import com.yukthi.webutils.common.models.BaseResponse;

/**
 * The Class BasicVersionResponse.
 */
public class BasicVersionResponse extends BaseResponse
{
	
	/**
	 *  The version.
	 **/
	private Integer version = 1;

	/**
	 * Instantiates a new basic version response.
	 */
	public BasicVersionResponse(Integer version)
	{
		this.version = version;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version)
	{
		this.version = version;
	}
}
