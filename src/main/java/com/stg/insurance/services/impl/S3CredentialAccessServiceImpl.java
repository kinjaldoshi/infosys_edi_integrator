/**
 * 
 */
package com.stg.insurance.services.impl;

import com.stg.insurance.services.S3CredentialsAccessService;

/**
 * @author kinjal.doshi
 *
 */
public class S3CredentialAccessServiceImpl implements S3CredentialsAccessService {
	
	private String secretName = "s3-makeathon";
    private String region = "us-east-1";
   

	/* (non-Javadoc)
	 * @see com.stg.insurance.services.S3CredentialsAccessService#getAccessKey()
	 */
	@Override
	public String getAccessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.stg.insurance.services.S3CredentialsAccessService#getSecretKey()
	 */
	@Override
	public String getSecretKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
