/*
 * Cloud Service API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package ru.mastkey.client.api;

import com.fasterxml.jackson.core.type.TypeReference;

import ru.mastkey.client.ApiException;
import ru.mastkey.client.ApiClient;
import ru.mastkey.client.BaseApi;
import ru.mastkey.client.Configuration;
import ru.mastkey.client.Pair;

import ru.mastkey.model.FileResponse;
import org.springframework.core.io.Resource;
import java.util.UUID;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-11-27T23:18:56.704637+03:00[Europe/Moscow]", comments = "Generator version: 7.10.0")
public class FileControllerApi extends BaseApi {

  public FileControllerApi() {
    super(Configuration.getDefaultApiClient());
  }

  public FileControllerApi(ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * 
   * 
   * @param fileId  (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteFile(UUID fileId) throws ApiException {
    this.deleteFile(fileId, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param fileId  (required)
   * @param additionalHeaders additionalHeaders for this call
   * @throws ApiException if fails to make API call
   */
  public void deleteFile(UUID fileId, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'fileId' is set
    if (fileId == null) {
      throw new ApiException(400, "Missing the required parameter 'fileId' when calling deleteFile");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/files/{fileId}"
      .replaceAll("\\{" + "fileId" + "\\}", apiClient.escapeString(apiClient.parameterToString(fileId)));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    apiClient.invokeAPI(
        localVarPath,
        "DELETE",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        null
    );
  }

  /**
   * 
   * 
   * @param fileId  (required)
   * @return Resource
   * @throws ApiException if fails to make API call
   */
  public Resource getFile(UUID fileId) throws ApiException {
    return this.getFile(fileId, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param fileId  (required)
   * @param additionalHeaders additionalHeaders for this call
   * @return Resource
   * @throws ApiException if fails to make API call
   */
  public Resource getFile(UUID fileId, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'fileId' is set
    if (fileId == null) {
      throw new ApiException(400, "Missing the required parameter 'fileId' when calling getFile");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/files/{fileId}"
      .replaceAll("\\{" + "fileId" + "\\}", apiClient.escapeString(apiClient.parameterToString(fileId)));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      "application/octet-stream"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    TypeReference<Resource> localVarReturnType = new TypeReference<Resource>() {};
    return apiClient.invokeAPI(
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        localVarReturnType
    );
  }

  /**
   * 
   * 
   * @param telegramUserId  (required)
   * @param pageNumber  (optional)
   * @param pageSize  (optional)
   * @return List&lt;FileResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public List<FileResponse> getFilesInfo(Long telegramUserId, Integer pageNumber, Integer pageSize) throws ApiException {
    return this.getFilesInfo(telegramUserId, pageNumber, pageSize, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param telegramUserId  (required)
   * @param pageNumber  (optional)
   * @param pageSize  (optional)
   * @param additionalHeaders additionalHeaders for this call
   * @return List&lt;FileResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public List<FileResponse> getFilesInfo(Long telegramUserId, Integer pageNumber, Integer pageSize, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'telegramUserId' is set
    if (telegramUserId == null) {
      throw new ApiException(400, "Missing the required parameter 'telegramUserId' when calling getFilesInfo");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/files/users/{telegramUserId}"
      .replaceAll("\\{" + "telegramUserId" + "\\}", apiClient.escapeString(apiClient.parameterToString(telegramUserId)));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPair("pageNumber", pageNumber));
    localVarQueryParams.addAll(apiClient.parameterToPair("pageSize", pageSize));
    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    TypeReference<List<FileResponse>> localVarReturnType = new TypeReference<List<FileResponse>>() {};
    return apiClient.invokeAPI(
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        localVarReturnType
    );
  }

  /**
   * 
   * 
   * @param telegramUserId  (required)
   * @param files Массив загружаемых файлов (будет использоваться Resource вместо File) (required)
   * @throws ApiException if fails to make API call
   */
  public void uploadFiles(Long telegramUserId, List<Resource> files) throws ApiException {
    this.uploadFiles(telegramUserId, files, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param telegramUserId  (required)
   * @param files Массив загружаемых файлов (будет использоваться Resource вместо File) (required)
   * @param additionalHeaders additionalHeaders for this call
   * @throws ApiException if fails to make API call
   */
  public void uploadFiles(Long telegramUserId, List<Resource> files, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'telegramUserId' is set
    if (telegramUserId == null) {
      throw new ApiException(400, "Missing the required parameter 'telegramUserId' when calling uploadFiles");
    }
    
    // verify the required parameter 'files' is set
    if (files == null) {
      throw new ApiException(400, "Missing the required parameter 'files' when calling uploadFiles");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/files/users/{telegramUserId}"
      .replaceAll("\\{" + "telegramUserId" + "\\}", apiClient.escapeString(apiClient.parameterToString(telegramUserId)));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarHeaderParams.putAll(additionalHeaders);

    
    if (files != null)
      localVarFormParams.put("files", files);

    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    apiClient.invokeAPI(
        localVarPath,
        "POST",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        null
    );
  }

  @Override
  public <T> T invokeAPI(String url, String method, Object request, TypeReference<T> returnType, Map<String, String> additionalHeaders) throws ApiException {
    String localVarPath = url.replace(apiClient.getBaseURL(), "");
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarHeaderParams.putAll(additionalHeaders);

    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    return apiClient.invokeAPI(
      localVarPath,
        method,
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        request,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        returnType
    );
  }
}
