package com.jimingqiang.study.elasticsearch;

import com.jimingqiang.study.elasticsearch.annotation.EsDocument;

import com.jimingqiang.study.elasticsearch.annotation.EsField;
import com.jimingqiang.study.elasticsearch.enums.EsFieldIndexEnum;
import com.jimingqiang.study.elasticsearch.enums.EsFieldTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * analyzed:默认选项，以标准的全文索引方式，分析字符串，完成索引。
 * not_analyzed:精确索引，不对字符串做分析，直接索引字段数据的精确内容。
 * no：不索引该字段。
 *
 * @Auther: libo
 * @Date: 2018/7/31 15:47
 * @Description:
 */
@Data
@EsDocument(indexName = "jmq", indexType = "test")
public class OrderEsBean implements Serializable {
    private String id;

    @EsField(name = "orderId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String orderId;
    @EsField(name = "orderCode",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String orderCode;
    @EsField(name = "endUserId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String endUserId;
    @EsField(name = "startTime",type = EsFieldTypeEnum.Date)
    private Date startTime;
    @EsField(name = "informId",type = EsFieldTypeEnum.String)
    private String informId;
    @EsField(name = "organId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String organId;
    @EsField(name = "orderTypeId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String orderTypeId;
    @EsField(name = "orderTypeDetailId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String orderTypeDetailId;
    @EsField(name = "procMode",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer procMode;
    @EsField(name = "orderMemo",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String orderMemo;
    @EsField(name = "sourceSystem",type = EsFieldTypeEnum.Integer)
    private Integer sourceSystem;
    @EsField(name = "informType",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer informType;
    @EsField(name = "informUserId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String informUserId;
    @EsField(name = "informUserName",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String informUserName;
    @EsField(name = "informTime",type = EsFieldTypeEnum.Date)
    private Date informTime;
    @EsField(name = "informPhone",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String informPhone;
    @EsField(name = "informMobile",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String informMobile;
    @EsField(name = "ownerRoomId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String ownerRoomId;
    @EsField(name = "ownerRoomSign",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String ownerRoomSign;
    @EsField(name = "ownerBuildingId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String ownerBuildingId;
    @EsField(name = "ownerRegionId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String ownerRegionId;
    @EsField(name = "informRoomId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String informRoomId;
    @EsField(name = "ownerRoomSign",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String informRoomSign;
    @EsField(name = "informBuildingId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String informBuildingId;
    @EsField(name = "informRegionId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String informRegionId;
    @EsField(name = "departmentId",type = EsFieldTypeEnum.String)
    private String departmentId;
    @EsField(name = "departmentName",type = EsFieldTypeEnum.String)
    private String departmentName;
    @EsField(name = "supplierId",type = EsFieldTypeEnum.String)
    private String supplierId;
    @EsField(name = "supplierName",type = EsFieldTypeEnum.String)
    private String supplierName;
    @EsField(name = "acceptTime",type = EsFieldTypeEnum.Date)
    private Date acceptTime;
    @EsField(name = "reserveTime",type = EsFieldTypeEnum.Date)
    private Date reserveTime;
    @EsField(name = "orderTime",type = EsFieldTypeEnum.Date)
    private Date orderTime;
    @EsField(name = "planStartTime",type = EsFieldTypeEnum.Date)
    private Date planStartTime;
    @EsField(name = "planEndTime",type = EsFieldTypeEnum.Date)
    private Date planEndTime;
    @EsField(name = "assignRoleId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String assignRoleId;
    @EsField(name = "assignRoleName",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String assignRoleName;
    @EsField(name = "assignUserId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String assignUserId;
    @EsField(name = "assignUserName",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String assignUserName;
    @EsField(name = "exeRoleId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String exeRoleId;
    @EsField(name = "exeRoleName",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String exeRoleName;
    @EsField(name = "exeUserId",type = EsFieldTypeEnum.String)
    private String exeUserId;
    @EsField(name = "exeUserName",type = EsFieldTypeEnum.String)
    private String exeUserName;
    @EsField(name = "customerEvaluate",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer customerEvaluate;
    @EsField(name = "customerIdea",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String customerIdea;
    @EsField(name = "remindersNum",type = EsFieldTypeEnum.Integer)
    private Integer remindersNum;
    @EsField(name = "isOwner",type = EsFieldTypeEnum.Integer)
    private Integer isOwner;
    @EsField(name = "ifCharge",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer ifCharge;
    @EsField(name = "chargeAmount",type = EsFieldTypeEnum.Double,index = EsFieldIndexEnum.NOT_ANALYZED)
    private BigDecimal chargeAmount;
    @EsField(name = "ifHang",type = EsFieldTypeEnum.Integer)
    private Integer ifHang;
    @EsField(name = "docType",type = EsFieldTypeEnum.Integer)
    private Integer docType;
    @EsField(name = "getType",type = EsFieldTypeEnum.Integer)
    private Integer getType;
    @EsField(name = "orderTag",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer orderTag;
    @EsField(name = "orderStatus",type = EsFieldTypeEnum.Integer,index = EsFieldIndexEnum.NOT_ANALYZED)
    private Integer orderStatus;
    @EsField(name = "createTime",type = EsFieldTypeEnum.Date)
    private Date createTime;
    @EsField(name = "createUserId",type = EsFieldTypeEnum.String,index = EsFieldIndexEnum.NOT_ANALYZED)
    private String createUserId;
    @EsField(name = "createUserName",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String createUserName;
    @EsField(name = "endUserName",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String endUserName;
    @EsField(name = "endTime",type = EsFieldTypeEnum.Date)
    private Date endTime;
    @EsField(name = "endType",type = EsFieldTypeEnum.Integer)
    private Integer endType;
    @EsField(name = "closeType",type = EsFieldTypeEnum.Integer)
    private Integer closeType;
    @EsField(name = "customerSign",type = EsFieldTypeEnum.String)
    private String customerSign;
    @EsField(name = "updateUserId",type = EsFieldTypeEnum.String)
    private String updateUserId;
    @EsField(name = "updateTime",type = EsFieldTypeEnum.Date)
    private Date updateTime;
    @EsField(name = "versionNum",type = EsFieldTypeEnum.Integer)
    private Integer versionNum;
    @EsField(name = "lastOrderTime",type = EsFieldTypeEnum.Date)
    private Date lastOrderTime;
    @EsField(name = "timestamp",type = EsFieldTypeEnum.Date)
    private Date timestamp;
    @EsField(name = "processModeId",type = EsFieldTypeEnum.String,index=EsFieldIndexEnum.NOT_ANALYZED)
    private String processModeId;
    @EsField(name = "sourceType",type = EsFieldTypeEnum.Integer)
    private Integer sourceType;
    @EsField(name = "notPromptFinishTag",type = EsFieldTypeEnum.Integer)
    private Integer notPromptFinishTag;
    @EsField(name = "evaluateCountFlag",type = EsFieldTypeEnum.Integer)
    private Integer evaluateCountFlag;
    @EsField(name = "closeMemo",type = EsFieldTypeEnum.String,analyzer = "ngram_analyzer")
    private String closeMemo;
    @EsField(name = "orderTagMemo",type = EsFieldTypeEnum.String)
    private String orderTagMemo;//工单标签名称 冗余字段255
    @EsField(name = "standProcessTime",type = EsFieldTypeEnum.Date)
    private Long standProcessTime;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        //this.setId(orderId);
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
        if (planEndTime != null)
            this.standProcessTime = planEndTime.getTime() - new Date().getTime();
    }
}
