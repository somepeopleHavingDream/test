#ifndef IVS_DRAWER_H
#define IVS_DRAWER_H

#include <time.h>

#ifdef _WIN32
#define IVSDRAWER_API
#define CALLMETHOD __stdcall
#else
#define IVSDRAWER_API
#define CALLMETHOD
#endif

#ifdef _WIN32 
#include <windows.h>
#define IVS_BOOL        BOOL
#define IVS_WND_REF 	HWND
#define IVS_DC_REF		HDC
#define IVS_COLOR		COLORREF
#define IVS_RECT		RECT
typedef POINT			IVSPOINT;
#else
#define IVS_BOOL        int
#define IVS_WND_REF     void*
#define IVS_DC_REF      void*

typedef struct __IVS_MAC_WND
{
    int nWidth;
    int nHeight;
}IVS_WND;

typedef struct __IVS_COLOR
{
    float cgred;
    float cggreen;
    float cgblue;
}IVS_COLOR;

typedef struct __IVSPOINT
{
    float x;
    float y;
}IVSPOINT;

typedef struct __IVS_RECT
{
    float left;
    float bottom;
    float right;
    float top;
}IVS_RECT;
#endif

#ifdef _WIN32
  #define INT64           __int64
#else
	#define DWORD       unsigned int
	#define INT64       long long
#endif

typedef enum
{
	SHOW_NONE = 0,
	SHOW_RULE = (1 << 0),
	SHOW_ALARM = (1 << 1),
	SHOW_TRACK = (1 << 2),
	SHOW_ALL = (SHOW_RULE | SHOW_ALARM | SHOW_TRACK),
}SHOW_TYPE;

typedef enum
{
	DRAW_JSON = 0,
	DRAW_TRACK,
	DRAW_ALARM,
	DRAW_RULE,
	DRAW_ALARMRULE,
	DRAW_ALARMEX,
	DRAW_TRACKEX,
	DRAW_MOVE_CHECK,
	DRAW_TEST = 9,
	DRAW_WEB_RULE = 11,
	DRAW_WEB_ALARM,
	DRAW_FLOW_INFO,
	DRAW_TRACKEX2,
	DRAW_WUXI235_TRACKEX2,
	DRAW_TRACKEXA1,
	DRAW_TRACKEX2_TYPE_HUMAN,
	DRAW_TRACKEX2_TYPE_VEHICLE,
	DRAW_TRACKEX2_TYPE_NONMOTOR,
	DRAW_TRACKEX2_TYPE_SHOPPRESENCE, 
	DRAW_TRACKEX2_TYPE_FLOWBUSINESS,
	DRAW_INTELFLOW,
	DRAW_SMARTMOTION,
	DRAW_END,
}DRAW_TYPE;

typedef enum
{
	IVS_LINGER = 0,				 
	IVS_TRACKTAIL,				// 轨迹尾巴
	IVS_GET_OBJECT,
	IVS_DISABLE_VIDEO_TIME,		// 禁止视频浓缩的时间，默认开启
	IVS_DISABLE_VIDEO_OBJECT,
	IVS_TRACK_OBJECT = 6,
	IVS_SET_PEN_HEIGHT,
	IVS_TRACK_OBJECT_EX = 8,
}IVS_CMD_TYPE;

typedef enum
{
    DRAW_PEN_DEFAULT = -1,
    DRAW_PEN_SOLID = 0,
    DRAW_PEN_DASH,                        
    DRAW_PEN_DOT,             
    DRAW_PEN_DASHDOT,         
    DRAW_PEN_DASHDOTDOT,       
    DRAW_PEN_NULL,             
    DRAW_PEN_INSIDEFRAME,     
    DRAW_PEN_USERSTYLE,        
    DRAW_PEN_ALTERNATE,        
}DRAW_PEN_STYLE;

#define IVS_MAX_ACTION_NUM		4
#define IVS_MAX_EVENT_NUM       64
#define IVS_MAX_POLYLINE_NUM	32
#define IVS_MAX_OBJECT_NUM		64
#define IVS_NAME_NUM		    128

// 二维空间点
typedef struct 
{
	short            nx;
	short            ny;
} DH_IVS_SPOINT, *LPDH_IVS_SPOINT;

// 物体对应图片文件信息
typedef struct  
{
	DWORD           dwOffSet;                       // 文件在二进制数据块中的偏移位置, 单位:字节
	DWORD           dwFileLenth;                    // 文件大小, 单位:字节
	WORD            wWidth;                         // 图片宽度, 单位:像素
	WORD            wHeight;                        // 图片高度, 单位:像素
	char*           pszFilePath;                    // 鉴于历史原因,该成员只在事件上报时有效
	// 文件路径
	// 用户使用该字段时需要自行申请空间进行拷贝保存
	BYTE            bIsDetected;                    // 图片是否算法检测出来的检测过的提交识别服务器时,
	// 则不需要再时检测定位抠图,1:检测过的,0:没有检测过
	BYTE			bReserved[3];					// 预留字节数
	int				nFilePathLen;					// 文件路径长度 既pszFilePath 用户申请的大小
	DH_IVS_SPOINT	stuPoint;						// 小图左上角在大图的位置，使用绝对坐标系				
}DH_IVS_PIC_INFO;

typedef struct tagDH_IVS_TIME_EX
{
	DWORD                dwYear;                  // 年
	DWORD                dwMonth;                 // 月
	DWORD                dwDay;                   // 日
	DWORD                dwHour;                  // 时
	DWORD                dwMinute;                // 分
	DWORD                dwSecond;                // 秒
	DWORD                dwMillisecond;           // 毫秒
	DWORD                dwUTC;                   // utc时间(获取时0表示无效，非0有效   下发无效)
	DWORD				 dwReserved[1];			  // 预留字段
} DH_IVS_TIME_EX,*LPDH_IVS_TIME_EX;
// 区域；各边距按整长8192的比例
typedef struct 
{
	long             left;
	long             top;
	long             right;
	long             bottom;
} DH_IVS_LRECT, *LPDH_IVS_LRECT;

#pragma pack(push)
#pragma pack(4)
// 视频分析物体信息结构体
typedef struct 
{
	int                 nObjectID;                          // 物体ID,每个ID表示一个唯一的物体
	char                szObjectType[128];                  // 物体类型
	int                 nConfidence;                        // 置信度(0~255),值越大表示置信度越高
	int                 nAction;                            // 物体动作:1:Appear 2:Move 3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
	DH_IVS_LRECT         BoundingBox;                        // 包围盒
	DH_IVS_SPOINT        Center;                             // 物体型心
	int                 nPolygonNum;                        // 多边形顶点个数
	DH_IVS_SPOINT        Contour[16];						// 较精确的轮廓多边形
	DWORD               rgbaMainColor;                      // 表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时, 其值为0x00ff0000.
	char                szText[128];                        // 物体上相关的带0结束符文本,比如车牌,集装箱号等等
	char                szObjectSubType[62];                // 物体子类别,根据不同的物体类型,可以取以下子类型：
	WORD                wColorLogoIndex;                    // 车标索引
	WORD                wSubBrand;                          // 车辆子品牌 需要通过映射表得到真正的子品牌 映射表详见开发手册
	BYTE                byReserved1;                     
	bool                bPicEnble;                          // 是否有物体对应图片文件信息
	DH_IVS_PIC_INFO     stPicInfo;                          // 物体对应图片信息
	bool                bShotFrame;                         // 是否是抓拍张的识别结果
	bool                bColor;                             // 物体颜色(rgbaMainColor)是否可用
	BYTE                byReserved2;
	BYTE                byTimeType;                         // 时间表示类型,详见EM_TIME_TYPE说明
	DH_IVS_TIME_EX     stuCurrentTime;                     // 针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
	DH_IVS_TIME_EX     stuStartTime;                       // 开始时间戳（物体开始出现时）
	DH_IVS_TIME_EX     stuEndTime;                         // 结束时间戳（物体最后出现时）
	DH_IVS_LRECT         stuOriginalBoundingBox;             // 包围盒(绝对坐标)
	DH_IVS_LRECT         stuSignBoundingBox;                 // 车标坐标包围盒
	DWORD               dwCurrentSequence;                  // 当前帧序号（抓下这个物体时的帧）
	DWORD               dwBeginSequence;                    // 开始帧序号（物体开始出现时的帧序号）
	DWORD               dwEndSequence;                      // 结束帧序号（物体消逝时的帧序号）
	INT64               nBeginFileOffset;                   // 开始时文件偏移, 单位: 字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
	INT64               nEndFileOffset;                     // 结束时文件偏移, 单位: 字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
	BYTE                byColorSimilar[8];					// 物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
	BYTE                byUpperBodyColorSimilar[8];			// 上半身物体颜色相似度(物体类型为人时有效)
	BYTE                byLowerBodyColorSimilar[8];			// 下半身物体颜色相似度(物体类型为人时有效)
	int                 nRelativeID;                        // 相关物体ID
	char                szSubText[20];                      // "ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
	WORD                wBrandYear;                         // 车辆品牌年款 需要通过映射表得到真正的年款 映射表详见开发手册
} DH_IVS_OBJECT_INFO;
#pragma pack(pop)

typedef struct IVS_CONFIG_EVENT
{
	char					szEventName[IVS_NAME_NUM];			// 事件名称，详见"事件类型列表"
	char					szRuleName[IVS_NAME_NUM];			// 规则名称，不同规则不能重名
	DH_IVS_OBJECT_INFO		stuObject;
	char					Context[28];
	int						alarmAction;					    // 与NetSDK定义一致
	int						alarmType;
}IVS_CONFIG_EVENT_INFO;

typedef struct IVS_CONFIG_EVENTEX
{
	char					szEventName[IVS_NAME_NUM];			// 事件名称，详见"事件类型列表"
	char					szRuleName[IVS_NAME_NUM];			// 规则名称，不同规则不能重名
	char					Context[28];					
	int						alarmAction;					    // 与NetSDK定义一致
	int						alarmType;
	int						nObjectNum;
	DH_IVS_OBJECT_INFO		stuObject[IVS_MAX_OBJECT_NUM];		    // 检测到的物体
	int						nContext;                           // 该字段未用，作为大类字段
	void*					pContext;
}IVS_CONFIG_EVENT_INFOEX;

typedef struct 
{
	int						nEventsNum;							// 视频分析规则数
	IVS_CONFIG_EVENT_INFO	stuEventInfo[IVS_MAX_EVENT_NUM];    // 事件信息
}IVS_CFG_ANALYSEVENTS_INFO;

typedef struct 
{
	int						nEventsNum;							// 视频分析规则数
	IVS_CONFIG_EVENT_INFOEX stuEventInfo[IVS_MAX_EVENT_NUM];    // 事件信息
}IVS_CFG_ANALYSEVENTS_INFOEX;

typedef struct
{
	int						nX;                                 // 8192坐标系
	int						nY;		
}IVS_CFG_POLYLINE;

typedef struct  __IVS_WEB_RULE
{
	int						size;
	int						nRuleType;
	int						nRuleEnable;
	int						ndirect1;
	int						ndirect2;
	int						nPoint1;
	int						nPoint2;
	unsigned char			bActionType[4];
	DH_IVS_OBJECT_INFO		objectMsg;
	char					szRuleName[IVS_NAME_NUM];
	IVS_CFG_POLYLINE		stuDetectLine1[IVS_MAX_POLYLINE_NUM];	//Line
	IVS_CFG_POLYLINE		stuDetectLine2[IVS_MAX_POLYLINE_NUM];	//Region	
	int                     nRuleID;
	IVS_CFG_POLYLINE		stuDirectionLine[2];					//Direction
	char					szRevered[236];
}IVS_WEB_RULE;

typedef struct __IVS_WEB_RULE_ARRAY
{
	int					    size;
	int					    nCount;
	IVS_WEB_RULE*		    pRule;
}IVS_WEB_RULE_ARRAY;

typedef struct
{
	int					objectid;
	struct tm			startTime;
	struct tm			endTime;
	INT64				nBeginFileOffset;				// 开始时文件偏移字节数(物体开始出现时，视频帧在原始视频文件中相对于文件起始处的偏移)
	INT64				nEndFileOffset;					// 结束时文件偏移字节数(物体消逝时，视频帧在原始视频文件中相对于文件起始处的偏移)
	int					classid;
}IVSOBJECT;

typedef struct
{
	IVS_BOOL            trackex2objtype;                // 是否显示物体类型，0:否，1:显示，默认不显示
    IVS_BOOL            trackex2attribute88;            // 是否显示0x88属性包，0:否，1:显示，默认显示
    IVS_BOOL            trackex2objid;                  // 是否显示物体ID，0:否，1:显示，默认不显示
    IVS_BOOL            trackex2humanage;	            // 是否显示具体年龄，0:显示年龄段，1:显示年龄，默认显示年龄段
}TrackEx2Configure;

typedef union
{
	IVSOBJECT			object;
	IVSPOINT			xPt;
	char				szReverd[128];
}ObjectContex;

typedef struct _DH_IVS_POINT
{
/*************************************************
** 轨迹点是物体外接矩形的中心
** 根据X,Y及XSize,YSize计算出的物体外接矩形坐标为
** (X-XSize, Y-YSize, X+XSize, Y+YSize)
**************************************************/
	short 		x; 
	short 		y; 
	short		xSize;
	short		ySize;
}DH_IVS_POINT; 

typedef struct  _RuleColor
{
	int ruletype;
	IVS_COLOR crColor;		
}RuleColor;

#ifdef __cplusplus
extern "C" {
#endif

/**
 * 启动IVSDraw(只调用一次)依赖IvsDrawer解析json数据时调用
 */
IVSDRAWER_API int CALLMETHOD DRAW_Startup();

/**
 * 退出IVSDraw
 */
IVSDRAWER_API void CALLMETHOD DRAW_Cleanup();

/**
 * 打开作图端口
 *
 * @param[in] nPort 作图端口
 * @return BOOL，成功返回TRUE，失败返回FALSE
 */
IVSDRAWER_API BOOL CALLMETHOD DRAW_Open(int nPort);

/**
 * 关闭作图端口
 *
 * @param[in] nPort 作图端口
 */
IVSDRAWER_API void CALLMETHOD DRAW_Close(int nPort);

/**
 * 重置作图端口
 *
 * @param[in] nPort 作图端口
 * @param[in] nType  详见DRAW_TYPE定义，仅DRAW_TRACK,DRAW_ALARM,DRAW_RULE有效
 * @return BOOL，成功返回TRUE，失败返回FALSE
 */
IVSDRAWER_API BOOL CALLMETHOD DRAW_Reset(int nPort, int nType);

/**
 * 输入jason格式字符串数据，原始数据未经过解析
 *
 * @param[in] nPort 作图端口
 * @param[in] pJsonData 数据内存地址
 * @param[in] nDataLen 数据长度
 * @param[in] nPort 作图端口
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputJsonData(int nPort, unsigned char* pJsonData, int nDataLen, int nFrameSeq);

/**
 * 设置规则颜色
 *
 * @param[in] nPort 作图端口
 * @param[in] nRuleColor 规则名称和规则颜色
 * @param[in] nRuleNum 规则数
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetRuleColor(int nPort,RuleColor* nRuleColor, int nRuleNum);

/**
 * 输入规则数据，经过jason解析
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 目前没有用到此字段
 * @param[in] pRuleData 数据内存地址
 * @param[in] nframe 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputRuleData(int nPort, int nType, unsigned char* pRuleData, int nframe);

/**
 * 输入轨迹数据，对应智能分析轨迹帧0xF1(表示辅助帧)0x05(轨迹帧数据)
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 目前没有用到此字段
 * @param[in] pTrackData 数据内存地址
 * @param[in] nDataLen 数据长度
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputTrackData(int nPort, int nType, unsigned char* pTrackData, int nDataLen,int nFrameSeq);

/**
 * 输入轨迹数据，智能结构化信息帧0xF1(表示辅助帧)0x0E(结构化数据)或者NVR浓缩信息轨迹点
 *
 * @param[in] nPort 作图端口
 * @param[in] nType nType=7表示智能结构化帧，nType=19表示智能客流，否则表示NVR浓缩信息轨迹点
 * @param[in] pTrackData 数据内存地址
 * @param[in] nDataLen 数据长度
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputTrackDataEx2(int nPort, int nType, unsigned char* pTrackData, int nDataLen,int nFrameSeq);

/**
 * 输入警报数据，经过jason解析
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 目前没有用到此字段
 * @param[in] pAlarmData 数据内存地址
 * @param[in] nDataLen 数据长度
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputAlarmData(int nPort, int nType, unsigned char* pAlarmData, int nDataLen);

/**
 * 输入警报数据，经过jason解析，与DRAW_InputAlarmData的差异在于数据结构不同
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 目前没有用到此字段
 * @param[in] pAlarmData 数据内存地址
 * @param[in] nDataLen 数据长度
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputAlarmDataEx(int nPort, int nType, unsigned char* pAlarmData, int nDataLen);

/**
 * 输入动检信息数据
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 目前没有用到此字段
 * @param[in] pData 动检信息数据
 * @param[in] nDataLen 数据长度
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputMoveCheckData(int nPort, int nType, unsigned char* pData, int nDataLen, int nFrameSeq);

/**
 * 指定位置显示一串字符串（可多行，以\n结尾）
 *
 * @param[in] nPort 作图端口
 * @param[in] pTextData 数据地址
 * @param[in] nTextNumPos pTextData数据对应的结构体个数
 * @return int，成功返回0，不成功返回-1
 */
typedef struct
{
    const char* pText;  // 显示的字符串
	IVSPOINT hPos;      // 显示位置
	int nFontsize;      // 字体大小
	int nRed;           // 字体颜色
	int nGreen;         // 字体颜色
	int nBlue;          // 字体颜色
    int nAlignMode;     // 对齐方式，0：左对齐，1：居中对齐，2：右对齐
}TextData;
IVSDRAWER_API int CALLMETHOD DRAW_InputTextData(int nPort, TextData* pTextData, int nTextNumPos);

/**
 * 执行作图操作
 *
 * @param[in] nPort 作图端口
 * @param[in] hDC 作图设备句柄
 * @param[in] hWnd 窗口句柄
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Draw(int nPort, IVS_DC_REF hDC, IVS_WND_REF hWnd, int nFrameSeq);

/**
 * 设置生存时间
 *
 * @param[in] nPort 作图端口
 * @param[in] nType  详见DRAW_TYPE
 * @param[in] nLifeCount 生存时间
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetLifeCount(int nPort, int nType, int nLifeCount);

/**
 * 设置使能
 *
 * @param[in] nPort 作图端口
 * @param[in] type 类型 参照DRAW_TYPE
 * @param[in] bEnable 使能标记
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetEnable(int nPort, int nType, IVS_BOOL bEnable);

/**
 * 创建画笔
 *
 * @param[in] nPort 作图端口
 * @param[in] nType 规则类型
 * @param[in] nPenStyle 画笔格式(实线or虚线)，详见DRAW_PEN_STYLE
 * @param[in] nWidth 画笔宽度
 * @param[in] crColor 画笔颜色
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetPen(int nPort, int nType, int nPenStyle, int nWidth, IVS_COLOR crColor);

/**
 * 执行命令操作
 *
 * @param[in] nPort 作图端口
 * @param[in] nCmdType 详见IVS_CMD_TYPE
 * @param[in] pContext 仅当nCmdType为IVS_TRACK_OBJECT/IVS_TRACK_OBJECT_EX或IVS_GET_OBJECT，指向ObjectContex结构体
 *                     其他指向int整型
 * @param[in] nContextSize pContext对应的结构体大小
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Ioctl(int nPort, int nCmdType, void* pContext, int nContextSize);

/**
 * 刷新帧序号
 *
 * @param[in] nPort 作图端口
 * @param[in] nFrameSeq 帧序号
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Refresh(int nPort, int nFrameSeq);

/**
 * 每调用一次，减少一次规则的生命周期(默认255)，减少到0时删除规则
 *
 * @param[in] nPort 作图端口
 */
IVSDRAWER_API void CALLMETHOD DRAW_Idle(int nPort);

/**
 * 清除作图内容
 *
 * @param[in] nPort 作图端口
 * @param[in] nReserved 预留参数
 */
IVSDRAWER_API void CALLMETHOD DRAW_Clean(int nPort, int nReserved);

/**
 * 设置轨迹开始时间（视频浓缩）
 *
 * @param[in] nPort 作图端口
 * @param[in] nTime 时间
 * @param[in] nObjectId 物体ID
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTime(int nPort, int nTime, int nObjectId);

/**
 * 设置显示类型，现有的类型有rule，alarm，track
 *
 * @param[in] nPort     作图端口
 * @param[in] nShowType 详见SHOW_TYPE，可组合使用
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetShowType(int nPort, int nShowType);

/**
 * 画track回调
 *
 * @param[in] nPort 作图端口
 * @param[in] pCallBack 回调函数
 * @param[in] pUserData 回调参数
 */
typedef void (CALLMETHOD* OnDrawOneTrack)(int nClassID, int nObjID, DH_IVS_POINT* pPoints, int nPointNum, void* pUserData);
IVSDRAWER_API void CALLMETHOD DRAW_SetDrawOneTrackCallback(int nPort, OnDrawOneTrack pCallBack, void* pUserData);

/**
 * 翻译字符串回调
 *
 * @param[in] nPort 作图端口
 * @param[in] pTranslateCBFunc 回调函数
 * @param[in] pUserData 回调参数
 */
typedef int (CALLMETHOD* fTranslateCallback)(unsigned char* strSrc,int nSrsLen, unsigned char* strDst, int* nDstLen, void* pUserData);
IVSDRAWER_API void CALLMETHOD DRAW_SetTranslateCallback(int nPort, fTranslateCallback pTranslateCBFunc, void* pUserData);

/**
 * 由此规则产生的track报警会被忽略
 *
 * @param[in] nPort 作图端口
 * @param[in] pRuleName 规则名
 * @param[in] bEnable 是否开启
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetRuleTrackAlarm(int nPort, const char* pRuleName, bool bEnable);

/**
 * 设置track的显示规则
 *
 * @param[in] nPort 作图端口
 * @param[in] nType: 0：显示所有track（默认方式），1：只显示有报警的track
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetShowTrackType(int nPort, int nType);

/**
 * 设置帧序号
 *
 * @param[in] nPort 作图端口
 * @param[in] nFrameSeq 帧序号
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetFrameNum(int nPort, int nFrameSeq);

/**
 * 根据{大类ID, 物体ID}设置跟踪物体颜色, 形状
 *
 * @param[in] nPort 作图端口
 * @param[in] nClassId 大类ID
 * @param[in] nObjectId 物体ID
 * @param[in] crColor 画笔颜色
 * @param[in] bSpecialShape 1:特殊形状, 0:默认矩形
 * @param[in] bSpecialSingle 1:只保留当前跟踪框的特殊形状，其他跟踪框回到默认形状 0：形状不变
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTrackObjectColor(int nPort, int nClassId, int nObjectId, IVS_COLOR crColor, bool bSpecialShape, bool bSpecialSingle);

/**
 * 根据bSpecialShape标志，设置车辆识别跟踪框形状
 *
 * @param[in] nPort 作图端口
 * @param[in] bSpecialShape 1:特殊形状, 0:默认矩形
 * @return int，成功返回0，不成功返回-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTrackEX2Sharp(int nPort, bool bSpecialShape);

/**
 * 根据trackex2config的设置来显示某些字段，默认是不显示的
 *
 * @param[in] nPort 作图端口
 * @param[in] trackex2config 控制是否显示某些字段
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetTrackEx2Config(int nPort, TrackEx2Configure trackex2config);

/**
 * 根据bEnable控制规则名称是否显示，默认显示
 *
 * @param[in] nPort 作图端口
 * @param[in] bEnable 控制字段
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetRuleNameConfig(int nPort, bool bEnable);

/**
 * 清除配置数据
 *
 * @param[in] nPort 作图端口
 * @param[in] nClearType 清除数据类型 详见IVS_CMD_TYPE
 */
IVSDRAWER_API void CALLMETHOD DRAW_ClearIVSConfigData(int nPort, int nClearType);

/**
 * 设置IVS的显示区域
 *
 * @param[in] nPort 作图端口
 * @param[in] rectRegion 显示区域
 * @param[in] bEnable 设置使能
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetIVSDisplayRegion(int nPort, IVS_RECT *rectRegion, bool bEnable);

/**
 * NACL与IOS平台特有，设置窗口参数接口
 *
 * @param[in] nPort 作图端口
 * @param[in] nX 窗口左下角X坐标
 * @param[in] nY 窗口左下角Y坐标
 * @param[in] nWidth 窗口矩形宽度
 * @param[in] nHeight 窗口矩形高度
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetDrawSurfaceParam(int nPort, int nX, int nY, int nWidth, int nHeight);

/**
 * MAC平台特有接口
 * 外部设置显示比例（兼容Retina高清屏幕）
 * @param[in] nPort 作图端口
 * @param[in] scale 显示比例
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetDisplayScale(int nPort, float scale);

/**
 * 无效接口
 */
IVSDRAWER_API int CALLMETHOD DRAW_GetLastError(int nPort);
IVSDRAWER_API int CALLMETHOD DRAW_InputJpegData(int nPort, int nType, unsigned char* pJpegData, int nDataLen, int nFrameSeq);


#ifdef __cplusplus
}
#endif

#endif
