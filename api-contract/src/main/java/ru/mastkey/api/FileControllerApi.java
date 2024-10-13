/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ru.mastkey.api;

import ru.mastkey.model.FileResponse;
import java.util.UUID;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-25T12:44:57.009593+03:00[Europe/Moscow]")
@Validated
@Tag(name = "file-controller", description = "the file-controller API")
public interface FileControllerApi {

    /**
     * DELETE /api/v1/files/{fileId}
     *
     * @param fileId  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteFile",
        tags = { "file-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/v1/files/{fileId}"
    )
    ResponseEntity<Void> deleteFile(
        @Parameter(name = "fileId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fileId") UUID fileId
    );


    /**
     * GET /api/v1/files/{fileId}
     *
     * @param fileId  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getFile",
        tags = { "file-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/octet-stream", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/files/{fileId}",
        produces = { "application/octet-stream" }
    )
    ResponseEntity<org.springframework.core.io.Resource> getFile(
        @Parameter(name = "fileId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fileId") UUID fileId
    );


    /**
     * GET /api/v1/files/users/{telegramUserId}
     *
     * @param telegramUserId  (required)
     * @param pageNumber  (optional)
     * @param pageSize  (optional)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getFilesInfo",
        tags = { "file-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FileResponse.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/files/users/{telegramUserId}",
        produces = { "application/json" }
    )
    ResponseEntity<List<FileResponse>> getFilesInfo(
        @Parameter(name = "telegramUserId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("telegramUserId") Long telegramUserId,
        @Parameter(name = "pageNumber", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
        @Parameter(name = "pageSize", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize
    );


    /**
     * POST /api/v1/files/users/{telegramUserId}
     *
     * @param telegramUserId  (required)
     * @param files  (optional)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "uploadFiles",
        tags = { "file-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/files/users/{telegramUserId}",
        consumes = { "multipart/form-data" }
    )
    ResponseEntity<Void> uploadFiles(
        @Parameter(name = "telegramUserId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("telegramUserId") Long telegramUserId,
        @Parameter(name = "files", description = "") @RequestPart(value = "files", required = false) List<MultipartFile> files
    );

}