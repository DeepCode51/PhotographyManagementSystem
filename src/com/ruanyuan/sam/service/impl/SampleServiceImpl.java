package com.ruanyuan.sam.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruanyuan.bas.dao.CollectDao;
import com.ruanyuan.bas.dao.CommentDao;
import com.ruanyuan.bas.pojo.Collect;
import com.ruanyuan.bas.pojo.Comment;
import com.ruanyuan.bus.dao.ApprovalDao;
import com.ruanyuan.bus.dao.SubscribeDao;
import com.ruanyuan.bus.pojo.Subscribe;
import com.ruanyuan.bus.vo.ApprovalVo;
import com.ruanyuan.sam.dao.ImageDao;
import com.ruanyuan.sam.dao.IntentionDao;
import com.ruanyuan.sam.dao.RotationDao;
import com.ruanyuan.sam.dao.SampleDao;
import com.ruanyuan.sam.pojo.Image;
import com.ruanyuan.sam.pojo.Intention;
import com.ruanyuan.sam.pojo.Rotation;
import com.ruanyuan.sam.pojo.Sample;
import com.ruanyuan.sam.service.SampleService;
import com.ruanyuan.sam.vo.SampleVo;
import com.ruanyuan.sys.constast.SysConstast;
import com.ruanyuan.sys.pojo.User;
import com.ruanyuan.sys.utils.AppFileUtils;
import com.ruanyuan.sys.utils.DataGridView;
import com.ruanyuan.sys.utils.WebUtils;
/**
 *   样片业务类
 * @author
 * @Data 2020年4月13日 下午8:00:01
 */
@Service
public class SampleServiceImpl implements SampleService {
	
	@Autowired
	private SampleDao sampleDao;
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private ApprovalDao approvalDao;
	@Autowired
	private CollectDao collectDao;
	@Autowired
	private IntentionDao intentionDao;
	@Autowired
	private RotationDao rotationDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private SubscribeDao subscribeDao;
	
	/**
	  *  查询样片信息
	 *@param sampleVo 样片包装
	 *@return 返回 layui封装得数据对象
	 */
	public DataGridView getAllSample(SampleVo sampleVo) {
		//开启分页
		Page<Object> page  = PageHelper.startPage(sampleVo.getPage(),sampleVo.getLimit());
		//根据条件查询样片数据
		List<Sample> data = this.sampleDao.getAllSample(sampleVo);
		//返回layui封装得 数据对象 
		return new DataGridView(page.getTotal(),data);
	}
	/**
	 * 添加修改下拉列表框
	 */
	public List<Sample> getAllSample1(SampleVo sampleVo) {
		// TODO Auto-generated method stub
		return sampleDao.getAllSample(sampleVo);
	}
	/**
	 * 添加样片
	 *@param sampleVo 样片包装类
	 */
	public void addSample(SampleVo sampleVo) {
		//添加样片
		String filePath=AppFileUtils.updateFileName(sampleVo.getSampleImage(),SysConstast.FILE_UPLOAD_TEMP);
		sampleVo.setSampleImage(filePath);
		//收藏
		sampleVo.setCollectionCount("0");
		//订单
		sampleVo.setOrderCount("0");
		//预约
		sampleVo.setSubscribeCount("0");
		//样片状态
		sampleVo.setSampleLogo(1);
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		sampleVo.setSampleTime(format);
		
		User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
		if(user!=null) {
			sampleVo.setUserId(user.getUserId());
		}
		sampleDao.insertSample(sampleVo);
	};
	/**
	 * 根据样片Id在线预约量加一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountAddOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleBySubscribeCountAddOne(sampleId);
		return count;
	};
	/**
	 * 根据样片Id在线预约量减一
	 *@return 受影响得行数
	 */
	public int updateSampleBySubscribeCountReduceOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleBySubscribeCountReduceOne(sampleId);
		return count;
	};
	/**
	 * 根据样片Id在线收藏量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountAddOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleByCollectionCountAddOne(sampleId);
		return count;
	};
	/**
	 * 根据样片Id在线收藏量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByCollectionCountReduceOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleByCollectionCountReduceOne(sampleId);
		return count;
	};
	/**
	 * 根据样片Id在线订单量加一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountAddOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleByOrderCountAddOne(sampleId);
		return count;
	};
	/**
	 * 根据样片Id在线订单量减一
	 *@return 受影响得行数
	 */
	public int updateSampleByOrderCountReduceOne(Integer sampleId) {
		int count = this.sampleDao.updateSampleByOrderCountReduceOne(sampleId);
		return count;
	}
	/**
	 *  前台展示样片信息
	 * @return DataGridView
	 */
	public DataGridView getPcSample() {
		List<Sample> data = sampleDao.getAllSample();
		return  new DataGridView(data);
	}

	
	/**
	 * 根据类别查询样片信息（pc）
	 * @param SampleVo 包装类
	 * @return  返回layui封装得 数据对象 
	 */
	public DataGridView getAllTypeSample(SampleVo sampleVo) {
		//根据条件查询样片数据
		List<Sample> data = this.sampleDao.getAllSample(sampleVo);
		//返回layui封装得 数据对象 
		return new DataGridView(data);
	}
	
	/**
	 * 根据样片id查询样片信息
	 */
	public Sample getSampleId(Integer sampleId) {
		return sampleDao.getSampleId(sampleId);
	}
	/**
	 * 变更为新增样片
	 *@param sampleId  样片Id
	 *@param sampleLogo  样片 要更改得样片状态
	 */
	public void updateSampleLogo(Integer sampleId, Integer sampleLogo) {
		SampleVo sampleVo = new SampleVo ();
		sampleVo.setSampleId(sampleId);
		sampleVo.setSampleLogo(sampleLogo);
		sampleDao.updateSampleBySampleId(sampleVo);
		User user = (User) WebUtils.getHttpSession().getAttribute("adminUser");
		ApprovalVo approvalVo = new ApprovalVo();
		//添加时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		approvalVo.setApprovalTime(format);
		//设置审批内容
		approvalVo.setApprovalCount(sampleLogo);
		//设置被审批人
		approvalVo.setApprovedPerson(user.getUserId());
		//设置审批人
		approvalVo.setApprover(user.getUserId());
		//设置审批状态 通过
		approvalVo.setApprovalState(2);
		//设置样片编号
		approvalVo.setSampleId(sampleId);
		//添加审批
		approvalDao.addApproval(approvalVo);
	}
	/**
	 * 根据样片Id删除样片
	 *@param sampleId 样片Id
	 */
	public Integer deleteSampleBySampleId(Integer sampleId) {
		Integer deleteSampleBySampleId=null;
		//根据样片Id查询图片
		List<Image> limage =  imageDao.getImageBySample(sampleId);
		//根据样片Id查询轮播图
		List<Rotation> lrotation =  rotationDao.getRotationBySampleId(sampleId);
		//根据样片Id查询意向样片
		List<Intention> lintention = intentionDao.getIntentionBySampleId(sampleId);
		//根据样片Id查询收藏
		List<Collect> lcollect = collectDao.getCollectBySampleId(sampleId);
		//根据样片Id查询评论表
		Comment comment = new Comment();
		comment.setSampleId(sampleId);
		List<Comment> lcomment = commentDao.getCommentBySampleId(comment);
		//根据样片Id查询预约表
		List<Subscribe> lsubscribe = subscribeDao.getSubscribeBySampleId1(sampleId);
		//判断删除
		if(!((limage!=null&&limage.size()>0)||(lrotation!=null&&lrotation.size()>0)||(lintention!=null&&lintention.size()>0)||
				(lcollect!=null&&lcollect.size()>0)||(lcomment!=null&&lcomment.size()>0)||(lsubscribe!=null&&lsubscribe.size()>0))) {
			//删除原来 的图
			Sample sample=this.sampleDao.getSampleId(sampleId);
			System.out.println(sample);
			AppFileUtils.removeFileByPath(sample.getSampleImage());
			deleteSampleBySampleId = sampleDao.deleteSampleBySampleId(sampleId);
			return deleteSampleBySampleId;
		}else {
			return deleteSampleBySampleId;
		}
	}
	/**
	 * 根据样片名称查询
	 */
	public Sample getSampleBySampleName(String sampleName) {
		return sampleDao.getSampleBySampleName(sampleName);
	}
	/**
	 * 修改样片
	 */
	public void updateSample(SampleVo sampleVo) {
		String sampleImage = sampleVo.getSampleImage();
		if(sampleImage.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
			String filePath=AppFileUtils.updateFileName(sampleVo.getSampleImage(),SysConstast.FILE_UPLOAD_TEMP);
			sampleVo.setSampleImage(filePath);
			//得到要删除的原来的样片对象
			Sample sample=this.sampleDao.getSampleId(sampleVo.getSampleId());
			//删除原来的图
			AppFileUtils.removeFileByPath(sample.getSampleImage());
		}
		sampleDao.updateSampleBySampleId(sampleVo);
	}
	@Override
	public List<Sample> getAllSample() {
		return sampleDao.getAllSample();
	}

}
