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

import ru.mastkey.model.CreateWorkspaceRequest;
import java.util.UUID;
import ru.mastkey.model.WorkspaceResponse;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-11-27T23:18:56.704637+03:00[Europe/Moscow]", comments = "Generator version: 7.10.0")
public class WorkspaceControllerApi extends BaseApi {

  public WorkspaceControllerApi() {
    super(Configuration.getDefaultApiClient());
  }

  public WorkspaceControllerApi(ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * 
   * 
   * @param workspaceId  (required)
   * @param newWorkspaceName  (required)
   * @return WorkspaceResponse
   * @throws ApiException if fails to make API call
   */
  public WorkspaceResponse changeWorkspaceName(UUID workspaceId, String newWorkspaceName) throws ApiException {
    return this.changeWorkspaceName(workspaceId, newWorkspaceName, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param workspaceId  (required)
   * @param newWorkspaceName  (required)
   * @param additionalHeaders additionalHeaders for this call
   * @return WorkspaceResponse
   * @throws ApiException if fails to make API call
   */
  public WorkspaceResponse changeWorkspaceName(UUID workspaceId, String newWorkspaceName, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'workspaceId' is set
    if (workspaceId == null) {
      throw new ApiException(400, "Missing the required parameter 'workspaceId' when calling changeWorkspaceName");
    }
    
    // verify the required parameter 'newWorkspaceName' is set
    if (newWorkspaceName == null) {
      throw new ApiException(400, "Missing the required parameter 'newWorkspaceName' when calling changeWorkspaceName");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/workspaces/{workspaceId}"
      .replaceAll("\\{" + "workspaceId" + "\\}", apiClient.escapeString(apiClient.parameterToString(workspaceId)));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPair("newWorkspaceName", newWorkspaceName));
    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    TypeReference<WorkspaceResponse> localVarReturnType = new TypeReference<WorkspaceResponse>() {};
    return apiClient.invokeAPI(
        localVarPath,
        "PATCH",
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
   * @param createWorkspaceRequest  (required)
   * @return WorkspaceResponse
   * @throws ApiException if fails to make API call
   */
  public WorkspaceResponse createWorkspace(CreateWorkspaceRequest createWorkspaceRequest) throws ApiException {
    return this.createWorkspace(createWorkspaceRequest, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param createWorkspaceRequest  (required)
   * @param additionalHeaders additionalHeaders for this call
   * @return WorkspaceResponse
   * @throws ApiException if fails to make API call
   */
  public WorkspaceResponse createWorkspace(CreateWorkspaceRequest createWorkspaceRequest, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = createWorkspaceRequest;
    
    // verify the required parameter 'createWorkspaceRequest' is set
    if (createWorkspaceRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'createWorkspaceRequest' when calling createWorkspace");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/workspaces";

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    TypeReference<WorkspaceResponse> localVarReturnType = new TypeReference<WorkspaceResponse>() {};
    return apiClient.invokeAPI(
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
        localVarReturnType
    );
  }

  /**
   * 
   * 
   * @param workspaceId  (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteWorkspace(UUID workspaceId) throws ApiException {
    this.deleteWorkspace(workspaceId, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param workspaceId  (required)
   * @param additionalHeaders additionalHeaders for this call
   * @throws ApiException if fails to make API call
   */
  public void deleteWorkspace(UUID workspaceId, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'workspaceId' is set
    if (workspaceId == null) {
      throw new ApiException(400, "Missing the required parameter 'workspaceId' when calling deleteWorkspace");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/workspaces/{workspaceId}"
      .replaceAll("\\{" + "workspaceId" + "\\}", apiClient.escapeString(apiClient.parameterToString(workspaceId)));

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
   * @param telegramUserId  (required)
   * @param pageNumber  (optional)
   * @param pageSize  (optional)
   * @return List&lt;WorkspaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public List<WorkspaceResponse> getWorkspaces(Long telegramUserId, Integer pageNumber, Integer pageSize) throws ApiException {
    return this.getWorkspaces(telegramUserId, pageNumber, pageSize, Collections.emptyMap());
  }


  /**
   * 
   * 
   * @param telegramUserId  (required)
   * @param pageNumber  (optional)
   * @param pageSize  (optional)
   * @param additionalHeaders additionalHeaders for this call
   * @return List&lt;WorkspaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public List<WorkspaceResponse> getWorkspaces(Long telegramUserId, Integer pageNumber, Integer pageSize, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'telegramUserId' is set
    if (telegramUserId == null) {
      throw new ApiException(400, "Missing the required parameter 'telegramUserId' when calling getWorkspaces");
    }
    
    // create path and map variables
    String localVarPath = "/api/v1/workspaces/users/{telegramUserId}"
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

    TypeReference<List<WorkspaceResponse>> localVarReturnType = new TypeReference<List<WorkspaceResponse>>() {};
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
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
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