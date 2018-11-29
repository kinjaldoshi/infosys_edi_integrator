/**
 * 
 */
package com.stg.insurance.services.impl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.stg.insurance.properties.S3Properties;
import com.stg.insurance.services.S3CredentialsAccessService;

/**
 * @author kinjal.doshi
 *
 */
@Component
public class S3CredentialAccessServiceImpl implements S3CredentialsAccessService {
	
	private String secretName = "s3-makeathon";
    private String region = "us-east-1";
    
    @Autowired
	S3Properties s3Properties;
   

	/* (non-Javadoc)
	 * @see com.stg.insurance.services.S3CredentialsAccessService#getAccessKey()
	 */
	@Override
	public String getAccessKey() {
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());
		
		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
		
		String secret, decodedBinarySecret;
		
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
	                    .withSecretId(secretName);
	    
	   
	    
	    GetSecretValueResult getSecretValueResult = null;
	    
	    try {
	        getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	        
	    } catch (DecryptionFailureException e) {
	    	 e.printStackTrace();
	        throw e;
	    } catch (InternalServiceErrorException e) {
	    	 e.printStackTrace();
	        throw e;
	    } catch (InvalidParameterException e) {
	    	 e.printStackTrace();
	        throw e;
	    } catch (InvalidRequestException e) {
	        e.printStackTrace();
	        throw e;
	    } catch (ResourceNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	    
	 // Decrypts secret using the associated KMS CMK.
	    // Depending on whether the secret is a string or binary, one of these fields will be populated.
	    if (getSecretValueResult.getSecretString() != null) {
	        secret = getSecretValueResult.getSecretString();
	        System.out.println(secret);
	    }
	    else {
	        decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
	        System.out.println(decodedBinarySecret);
	    }
	    
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
