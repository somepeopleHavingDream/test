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
	IVS_TRACKTAIL,				// �켣β��
	IVS_GET_OBJECT,
	IVS_DISABLE_VIDEO_TIME,		// ��ֹ��ƵŨ����ʱ�䣬Ĭ�Ͽ���
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

// ��ά�ռ��
typedef struct 
{
	short            nx;
	short            ny;
} DH_IVS_SPOINT, *LPDH_IVS_SPOINT;

// �����ӦͼƬ�ļ���Ϣ
typedef struct  
{
	DWORD           dwOffSet;                       // �ļ��ڶ��������ݿ��е�ƫ��λ��, ��λ:�ֽ�
	DWORD           dwFileLenth;                    // �ļ���С, ��λ:�ֽ�
	WORD            wWidth;                         // ͼƬ���, ��λ:����
	WORD            wHeight;                        // ͼƬ�߶�, ��λ:����
	char*           pszFilePath;                    // ������ʷԭ��,�ó�Աֻ���¼��ϱ�ʱ��Ч
	// �ļ�·��
	// �û�ʹ�ø��ֶ�ʱ��Ҫ��������ռ���п�������
	BYTE            bIsDetected;                    // ͼƬ�Ƿ��㷨�������ļ������ύʶ�������ʱ,
	// ����Ҫ��ʱ��ⶨλ��ͼ,1:������,0:û�м���
	BYTE			bReserved[3];					// Ԥ���ֽ���
	int				nFilePathLen;					// �ļ�·������ ��pszFilePath �û�����Ĵ�С
	DH_IVS_SPOINT	stuPoint;						// Сͼ���Ͻ��ڴ�ͼ��λ�ã�ʹ�þ�������ϵ				
}DH_IVS_PIC_INFO;

typedef struct tagDH_IVS_TIME_EX
{
	DWORD                dwYear;                  // ��
	DWORD                dwMonth;                 // ��
	DWORD                dwDay;                   // ��
	DWORD                dwHour;                  // ʱ
	DWORD                dwMinute;                // ��
	DWORD                dwSecond;                // ��
	DWORD                dwMillisecond;           // ����
	DWORD                dwUTC;                   // utcʱ��(��ȡʱ0��ʾ��Ч����0��Ч   �·���Ч)
	DWORD				 dwReserved[1];			  // Ԥ���ֶ�
} DH_IVS_TIME_EX,*LPDH_IVS_TIME_EX;
// ���򣻸��߾ఴ����8192�ı���
typedef struct 
{
	long             left;
	long             top;
	long             right;
	long             bottom;
} DH_IVS_LRECT, *LPDH_IVS_LRECT;

#pragma pack(push)
#pragma pack(4)
// ��Ƶ����������Ϣ�ṹ��
typedef struct 
{
	int                 nObjectID;                          // ����ID,ÿ��ID��ʾһ��Ψһ������
	char                szObjectType[128];                  // ��������
	int                 nConfidence;                        // ���Ŷ�(0~255),ֵԽ���ʾ���Ŷ�Խ��
	int                 nAction;                            // ���嶯��:1:Appear 2:Move 3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
	DH_IVS_LRECT         BoundingBox;                        // ��Χ��
	DH_IVS_SPOINT        Center;                             // ��������
	int                 nPolygonNum;                        // ����ζ������
	DH_IVS_SPOINT        Contour[16];						// �Ͼ�ȷ�����������
	DWORD               rgbaMainColor;                      // ��ʾ���ơ������������Ҫ��ɫ�����ֽڱ�ʾ,�ֱ�Ϊ�졢�̡�����͸����,����:RGBֵΪ(0,255,0),͸����Ϊ0ʱ, ��ֵΪ0x00ff0000.
	char                szText[128];                        // ��������صĴ�0�������ı�,���糵��,��װ��ŵȵ�
	char                szObjectSubType[62];                // ���������,���ݲ�ͬ����������,����ȡ���������ͣ�
	WORD                wColorLogoIndex;                    // ��������
	WORD                wSubBrand;                          // ������Ʒ�� ��Ҫͨ��ӳ���õ���������Ʒ�� ӳ�����������ֲ�
	BYTE                byReserved1;                     
	bool                bPicEnble;                          // �Ƿ��������ӦͼƬ�ļ���Ϣ
	DH_IVS_PIC_INFO     stPicInfo;                          // �����ӦͼƬ��Ϣ
	bool                bShotFrame;                         // �Ƿ���ץ���ŵ�ʶ����
	bool                bColor;                             // ������ɫ(rgbaMainColor)�Ƿ����
	BYTE                byReserved2;
	BYTE                byTimeType;                         // ʱ���ʾ����,���EM_TIME_TYPE˵��
	DH_IVS_TIME_EX     stuCurrentTime;                     // �����ƵŨ��,��ǰʱ���������ץ�Ļ�ʶ��ʱ,�Ὣ��ʶ������֡����һ����Ƶ֡��jpegͼƬ��,��֡����ԭʼ��Ƶ�еĳ���ʱ�䣩
	DH_IVS_TIME_EX     stuStartTime;                       // ��ʼʱ��������忪ʼ����ʱ��
	DH_IVS_TIME_EX     stuEndTime;                         // ����ʱ���������������ʱ��
	DH_IVS_LRECT         stuOriginalBoundingBox;             // ��Χ��(��������)
	DH_IVS_LRECT         stuSignBoundingBox;                 // ���������Χ��
	DWORD               dwCurrentSequence;                  // ��ǰ֡��ţ�ץ���������ʱ��֡��
	DWORD               dwBeginSequence;                    // ��ʼ֡��ţ����忪ʼ����ʱ��֡��ţ�
	DWORD               dwEndSequence;                      // ����֡��ţ���������ʱ��֡��ţ�
	INT64               nBeginFileOffset;                   // ��ʼʱ�ļ�ƫ��, ��λ: �ֽڣ����忪ʼ����ʱ,��Ƶ֡��ԭʼ��Ƶ�ļ���������ļ���ʼ����ƫ�ƣ�
	INT64               nEndFileOffset;                     // ����ʱ�ļ�ƫ��, ��λ: �ֽڣ���������ʱ,��Ƶ֡��ԭʼ��Ƶ�ļ���������ļ���ʼ����ƫ�ƣ�
	BYTE                byColorSimilar[8];					// ������ɫ���ƶ�,ȡֵ��Χ��0-100,�����±�ֵ����ĳ����ɫ,���EM_COLOR_TYPE
	BYTE                byUpperBodyColorSimilar[8];			// �ϰ���������ɫ���ƶ�(��������Ϊ��ʱ��Ч)
	BYTE                byLowerBodyColorSimilar[8];			// �°���������ɫ���ƶ�(��������Ϊ��ʱ��Ч)
	int                 nRelativeID;                        // �������ID
	char                szSubText[20];                      // "ObjectType"Ϊ"Vehicle"����"Logo"ʱ,��ʾ�����µ�ĳһ��ϵ,����µ�A6L,���ڳ�ϵ�϶�,SDKʵ��ʱ͸�����ֶ�,�豸��ʵ��д��
	WORD                wBrandYear;                         // ����Ʒ����� ��Ҫͨ��ӳ���õ���������� ӳ�����������ֲ�
} DH_IVS_OBJECT_INFO;
#pragma pack(pop)

typedef struct IVS_CONFIG_EVENT
{
	char					szEventName[IVS_NAME_NUM];			// �¼����ƣ����"�¼������б�"
	char					szRuleName[IVS_NAME_NUM];			// �������ƣ���ͬ����������
	DH_IVS_OBJECT_INFO		stuObject;
	char					Context[28];
	int						alarmAction;					    // ��NetSDK����һ��
	int						alarmType;
}IVS_CONFIG_EVENT_INFO;

typedef struct IVS_CONFIG_EVENTEX
{
	char					szEventName[IVS_NAME_NUM];			// �¼����ƣ����"�¼������б�"
	char					szRuleName[IVS_NAME_NUM];			// �������ƣ���ͬ����������
	char					Context[28];					
	int						alarmAction;					    // ��NetSDK����һ��
	int						alarmType;
	int						nObjectNum;
	DH_IVS_OBJECT_INFO		stuObject[IVS_MAX_OBJECT_NUM];		    // ��⵽������
	int						nContext;                           // ���ֶ�δ�ã���Ϊ�����ֶ�
	void*					pContext;
}IVS_CONFIG_EVENT_INFOEX;

typedef struct 
{
	int						nEventsNum;							// ��Ƶ����������
	IVS_CONFIG_EVENT_INFO	stuEventInfo[IVS_MAX_EVENT_NUM];    // �¼���Ϣ
}IVS_CFG_ANALYSEVENTS_INFO;

typedef struct 
{
	int						nEventsNum;							// ��Ƶ����������
	IVS_CONFIG_EVENT_INFOEX stuEventInfo[IVS_MAX_EVENT_NUM];    // �¼���Ϣ
}IVS_CFG_ANALYSEVENTS_INFOEX;

typedef struct
{
	int						nX;                                 // 8192����ϵ
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
	INT64				nBeginFileOffset;				// ��ʼʱ�ļ�ƫ���ֽ���(���忪ʼ����ʱ����Ƶ֡��ԭʼ��Ƶ�ļ���������ļ���ʼ����ƫ��)
	INT64				nEndFileOffset;					// ����ʱ�ļ�ƫ���ֽ���(��������ʱ����Ƶ֡��ԭʼ��Ƶ�ļ���������ļ���ʼ����ƫ��)
	int					classid;
}IVSOBJECT;

typedef struct
{
	IVS_BOOL            trackex2objtype;                // �Ƿ���ʾ�������ͣ�0:��1:��ʾ��Ĭ�ϲ���ʾ
    IVS_BOOL            trackex2attribute88;            // �Ƿ���ʾ0x88���԰���0:��1:��ʾ��Ĭ����ʾ
    IVS_BOOL            trackex2objid;                  // �Ƿ���ʾ����ID��0:��1:��ʾ��Ĭ�ϲ���ʾ
    IVS_BOOL            trackex2humanage;	            // �Ƿ���ʾ�������䣬0:��ʾ����Σ�1:��ʾ���䣬Ĭ����ʾ�����
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
** �켣����������Ӿ��ε�����
** ����X,Y��XSize,YSize�������������Ӿ�������Ϊ
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
 * ����IVSDraw(ֻ����һ��)����IvsDrawer����json����ʱ����
 */
IVSDRAWER_API int CALLMETHOD DRAW_Startup();

/**
 * �˳�IVSDraw
 */
IVSDRAWER_API void CALLMETHOD DRAW_Cleanup();

/**
 * ����ͼ�˿�
 *
 * @param[in] nPort ��ͼ�˿�
 * @return BOOL���ɹ�����TRUE��ʧ�ܷ���FALSE
 */
IVSDRAWER_API BOOL CALLMETHOD DRAW_Open(int nPort);

/**
 * �ر���ͼ�˿�
 *
 * @param[in] nPort ��ͼ�˿�
 */
IVSDRAWER_API void CALLMETHOD DRAW_Close(int nPort);

/**
 * ������ͼ�˿�
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType  ���DRAW_TYPE���壬��DRAW_TRACK,DRAW_ALARM,DRAW_RULE��Ч
 * @return BOOL���ɹ�����TRUE��ʧ�ܷ���FALSE
 */
IVSDRAWER_API BOOL CALLMETHOD DRAW_Reset(int nPort, int nType);

/**
 * ����jason��ʽ�ַ������ݣ�ԭʼ����δ��������
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] pJsonData �����ڴ��ַ
 * @param[in] nDataLen ���ݳ���
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputJsonData(int nPort, unsigned char* pJsonData, int nDataLen, int nFrameSeq);

/**
 * ���ù�����ɫ
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nRuleColor �������ƺ͹�����ɫ
 * @param[in] nRuleNum ������
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetRuleColor(int nPort,RuleColor* nRuleColor, int nRuleNum);

/**
 * ����������ݣ�����jason����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType Ŀǰû���õ����ֶ�
 * @param[in] pRuleData �����ڴ��ַ
 * @param[in] nframe ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputRuleData(int nPort, int nType, unsigned char* pRuleData, int nframe);

/**
 * ����켣���ݣ���Ӧ���ܷ����켣֡0xF1(��ʾ����֡)0x05(�켣֡����)
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType Ŀǰû���õ����ֶ�
 * @param[in] pTrackData �����ڴ��ַ
 * @param[in] nDataLen ���ݳ���
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputTrackData(int nPort, int nType, unsigned char* pTrackData, int nDataLen,int nFrameSeq);

/**
 * ����켣���ݣ����ܽṹ����Ϣ֡0xF1(��ʾ����֡)0x0E(�ṹ������)����NVRŨ����Ϣ�켣��
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType nType=7��ʾ���ܽṹ��֡��nType=19��ʾ���ܿ����������ʾNVRŨ����Ϣ�켣��
 * @param[in] pTrackData �����ڴ��ַ
 * @param[in] nDataLen ���ݳ���
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputTrackDataEx2(int nPort, int nType, unsigned char* pTrackData, int nDataLen,int nFrameSeq);

/**
 * ���뾯�����ݣ�����jason����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType Ŀǰû���õ����ֶ�
 * @param[in] pAlarmData �����ڴ��ַ
 * @param[in] nDataLen ���ݳ���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputAlarmData(int nPort, int nType, unsigned char* pAlarmData, int nDataLen);

/**
 * ���뾯�����ݣ�����jason��������DRAW_InputAlarmData�Ĳ����������ݽṹ��ͬ
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType Ŀǰû���õ����ֶ�
 * @param[in] pAlarmData �����ڴ��ַ
 * @param[in] nDataLen ���ݳ���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputAlarmDataEx(int nPort, int nType, unsigned char* pAlarmData, int nDataLen);

/**
 * ���붯����Ϣ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType Ŀǰû���õ����ֶ�
 * @param[in] pData ������Ϣ����
 * @param[in] nDataLen ���ݳ���
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_InputMoveCheckData(int nPort, int nType, unsigned char* pData, int nDataLen, int nFrameSeq);

/**
 * ָ��λ����ʾһ���ַ������ɶ��У���\n��β��
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] pTextData ���ݵ�ַ
 * @param[in] nTextNumPos pTextData���ݶ�Ӧ�Ľṹ�����
 * @return int���ɹ�����0�����ɹ�����-1
 */
typedef struct
{
    const char* pText;  // ��ʾ���ַ���
	IVSPOINT hPos;      // ��ʾλ��
	int nFontsize;      // �����С
	int nRed;           // ������ɫ
	int nGreen;         // ������ɫ
	int nBlue;          // ������ɫ
    int nAlignMode;     // ���뷽ʽ��0������룬1�����ж��룬2���Ҷ���
}TextData;
IVSDRAWER_API int CALLMETHOD DRAW_InputTextData(int nPort, TextData* pTextData, int nTextNumPos);

/**
 * ִ����ͼ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] hDC ��ͼ�豸���
 * @param[in] hWnd ���ھ��
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Draw(int nPort, IVS_DC_REF hDC, IVS_WND_REF hWnd, int nFrameSeq);

/**
 * ��������ʱ��
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType  ���DRAW_TYPE
 * @param[in] nLifeCount ����ʱ��
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetLifeCount(int nPort, int nType, int nLifeCount);

/**
 * ����ʹ��
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] type ���� ����DRAW_TYPE
 * @param[in] bEnable ʹ�ܱ��
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetEnable(int nPort, int nType, IVS_BOOL bEnable);

/**
 * ��������
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType ��������
 * @param[in] nPenStyle ���ʸ�ʽ(ʵ��or����)�����DRAW_PEN_STYLE
 * @param[in] nWidth ���ʿ��
 * @param[in] crColor ������ɫ
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetPen(int nPort, int nType, int nPenStyle, int nWidth, IVS_COLOR crColor);

/**
 * ִ���������
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nCmdType ���IVS_CMD_TYPE
 * @param[in] pContext ����nCmdTypeΪIVS_TRACK_OBJECT/IVS_TRACK_OBJECT_EX��IVS_GET_OBJECT��ָ��ObjectContex�ṹ��
 *                     ����ָ��int����
 * @param[in] nContextSize pContext��Ӧ�Ľṹ���С
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Ioctl(int nPort, int nCmdType, void* pContext, int nContextSize);

/**
 * ˢ��֡���
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nFrameSeq ֡���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_Refresh(int nPort, int nFrameSeq);

/**
 * ÿ����һ�Σ�����һ�ι������������(Ĭ��255)�����ٵ�0ʱɾ������
 *
 * @param[in] nPort ��ͼ�˿�
 */
IVSDRAWER_API void CALLMETHOD DRAW_Idle(int nPort);

/**
 * �����ͼ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nReserved Ԥ������
 */
IVSDRAWER_API void CALLMETHOD DRAW_Clean(int nPort, int nReserved);

/**
 * ���ù켣��ʼʱ�䣨��ƵŨ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nTime ʱ��
 * @param[in] nObjectId ����ID
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTime(int nPort, int nTime, int nObjectId);

/**
 * ������ʾ���ͣ����е�������rule��alarm��track
 *
 * @param[in] nPort     ��ͼ�˿�
 * @param[in] nShowType ���SHOW_TYPE�������ʹ��
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetShowType(int nPort, int nShowType);

/**
 * ��track�ص�
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] pCallBack �ص�����
 * @param[in] pUserData �ص�����
 */
typedef void (CALLMETHOD* OnDrawOneTrack)(int nClassID, int nObjID, DH_IVS_POINT* pPoints, int nPointNum, void* pUserData);
IVSDRAWER_API void CALLMETHOD DRAW_SetDrawOneTrackCallback(int nPort, OnDrawOneTrack pCallBack, void* pUserData);

/**
 * �����ַ����ص�
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] pTranslateCBFunc �ص�����
 * @param[in] pUserData �ص�����
 */
typedef int (CALLMETHOD* fTranslateCallback)(unsigned char* strSrc,int nSrsLen, unsigned char* strDst, int* nDstLen, void* pUserData);
IVSDRAWER_API void CALLMETHOD DRAW_SetTranslateCallback(int nPort, fTranslateCallback pTranslateCBFunc, void* pUserData);

/**
 * �ɴ˹��������track�����ᱻ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] pRuleName ������
 * @param[in] bEnable �Ƿ���
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetRuleTrackAlarm(int nPort, const char* pRuleName, bool bEnable);

/**
 * ����track����ʾ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nType: 0����ʾ����track��Ĭ�Ϸ�ʽ����1��ֻ��ʾ�б�����track
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetShowTrackType(int nPort, int nType);

/**
 * ����֡���
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nFrameSeq ֡���
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetFrameNum(int nPort, int nFrameSeq);

/**
 * ����{����ID, ����ID}���ø���������ɫ, ��״
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nClassId ����ID
 * @param[in] nObjectId ����ID
 * @param[in] crColor ������ɫ
 * @param[in] bSpecialShape 1:������״, 0:Ĭ�Ͼ���
 * @param[in] bSpecialSingle 1:ֻ������ǰ���ٿ��������״���������ٿ�ص�Ĭ����״ 0����״����
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTrackObjectColor(int nPort, int nClassId, int nObjectId, IVS_COLOR crColor, bool bSpecialShape, bool bSpecialSingle);

/**
 * ����bSpecialShape��־�����ó���ʶ����ٿ���״
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] bSpecialShape 1:������״, 0:Ĭ�Ͼ���
 * @return int���ɹ�����0�����ɹ�����-1
 */
IVSDRAWER_API int CALLMETHOD DRAW_SetTrackEX2Sharp(int nPort, bool bSpecialShape);

/**
 * ����trackex2config����������ʾĳЩ�ֶΣ�Ĭ���ǲ���ʾ��
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] trackex2config �����Ƿ���ʾĳЩ�ֶ�
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetTrackEx2Config(int nPort, TrackEx2Configure trackex2config);

/**
 * ����bEnable���ƹ��������Ƿ���ʾ��Ĭ����ʾ
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] bEnable �����ֶ�
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetRuleNameConfig(int nPort, bool bEnable);

/**
 * �����������
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nClearType ����������� ���IVS_CMD_TYPE
 */
IVSDRAWER_API void CALLMETHOD DRAW_ClearIVSConfigData(int nPort, int nClearType);

/**
 * ����IVS����ʾ����
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] rectRegion ��ʾ����
 * @param[in] bEnable ����ʹ��
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetIVSDisplayRegion(int nPort, IVS_RECT *rectRegion, bool bEnable);

/**
 * NACL��IOSƽ̨���У����ô��ڲ����ӿ�
 *
 * @param[in] nPort ��ͼ�˿�
 * @param[in] nX �������½�X����
 * @param[in] nY �������½�Y����
 * @param[in] nWidth ���ھ��ο��
 * @param[in] nHeight ���ھ��θ߶�
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetDrawSurfaceParam(int nPort, int nX, int nY, int nWidth, int nHeight);

/**
 * MACƽ̨���нӿ�
 * �ⲿ������ʾ����������Retina������Ļ��
 * @param[in] nPort ��ͼ�˿�
 * @param[in] scale ��ʾ����
 */
IVSDRAWER_API void CALLMETHOD DRAW_SetDisplayScale(int nPort, float scale);

/**
 * ��Ч�ӿ�
 */
IVSDRAWER_API int CALLMETHOD DRAW_GetLastError(int nPort);
IVSDRAWER_API int CALLMETHOD DRAW_InputJpegData(int nPort, int nType, unsigned char* pJpegData, int nDataLen, int nFrameSeq);


#ifdef __cplusplus
}
#endif

#endif
