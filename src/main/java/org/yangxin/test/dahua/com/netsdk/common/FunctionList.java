package org.yangxin.test.dahua.com.netsdk.common;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.netsdk.demo.frame.*;
import com.netsdk.demo.frame.Attendance.Attendance;
import com.netsdk.demo.frame.AutoRegister.AutoRegister;
import com.netsdk.demo.frame.FaceRecognition.FaceRecognition;
import com.netsdk.demo.frame.FaceRecognition.LatticeScreen;
import com.netsdk.demo.frame.Gate.Gate;
import com.netsdk.demo.frame.ThermalCamera.ThermalCamera;
import com.netsdk.demo.frame.vto.VTODemo;

/**
 * 功能列表界面
 */
public class FunctionList extends JFrame {
	private static final long serialVersionUID = 1L;

	public FunctionList() {
		setTitle(Res.string().getFunctionList());
		setLayout(new BorderLayout());
		pack();
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);

		add(new FunctionPanel(), BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public class FunctionPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public FunctionPanel() {
			setLayout(new GridLayout(9, 2));

			setBorder(new EmptyBorder(30, 50, 0, 50));

			faceRecognitionBtn = new JButton(Res.string().getFaceRecognition());
			gateBtn = new JButton(Res.string().getGate());
			capturePictureBtn = new JButton(Res.string().getCapturePicture());
			realPlayBtn = new JButton(Res.string().getRealplay());
			itsEventBtn = new JButton(Res.string().getITSEvent());
			downloadBtn = new JButton(Res.string().getDownloadRecord());
			talkBtn = new JButton(Res.string().getTalk());
			deviceSearchAndInitBtn = new JButton(Res.string().getDeviceSearchAndInit());
			ptzBtn = new JButton(Res.string().getPTZ());
			deviceCtlBtn = new JButton(Res.string().getDeviceControl());
			alarmListenBtn = new JButton(Res.string().getAlarmListen());
			autoRegisterBtn = new JButton(Res.string().getAutoRegister());
			attendanceBtn = new JButton(Res.string().getAttendance());
			thermalCameraBtn = new JButton(Res.string().getThermalCamera());
			matrixScreenBtn = new JButton(Res.string().getmatrixScreen());
			humanNumberStatisticBtn = new JButton(Res.string().getHumanNumberStatistic());
			vtoBtn = new JButton(Res.string().getVTO());
			add(gateBtn);
			add(faceRecognitionBtn);
			add(deviceSearchAndInitBtn);
			add(ptzBtn);
			add(realPlayBtn);
			add(capturePictureBtn);
			add(talkBtn);
			add(itsEventBtn);
			add(downloadBtn);
			add(deviceCtlBtn);
			add(alarmListenBtn);
			add(autoRegisterBtn);
			add(attendanceBtn);
			add(thermalCameraBtn);
			add(matrixScreenBtn);
			add(humanNumberStatisticBtn);
			add(vtoBtn);
			gateBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							Gate.main(null);
						}
					});
				}
			});

			faceRecognitionBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							FaceRecognition.main(null);
						}
					});
				}
			});


			capturePictureBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							CapturePicture.main(null);
						}
					});
				}
			});

			realPlayBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							RealPlay.main(null);
						}
					});
				}
			});

			downloadBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							DownLoadRecord.main(null);
						}
					});
				}
			});

			talkBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							Talk.main(null);
						}
					});
				}
			});

			itsEventBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							TrafficEvent.main(null);
						}
					});
				}
			});

			deviceSearchAndInitBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							DeviceSearchAndInit.main(null);
						}
					});
				}
			});

			ptzBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							PTZControl.main(null);
						}
					});
				}
			});

			deviceCtlBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							DeviceControl.main(null);
						}
					});
				}
			});

			alarmListenBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							AlarmListen.main(null);
						}
					});
				}
			});

			autoRegisterBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							AutoRegister.main(null);
						}
					});
				}
			});

			attendanceBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							Attendance.main(null);
						}
					});
				}
			});

			thermalCameraBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							dispose();
							ThermalCamera.main(null);
						}
					});
				}
			});

			
			  matrixScreenBtn.addActionListener(new ActionListener() {
				  @Override public void actionPerformed(ActionEvent e) {
					  SwingUtilities.invokeLater(new Runnable() { 
						  public void run() 
						  { 
							   dispose();
							   LatticeScreen.main(null);					           
					           } 
						  });
					  }
					  });			  
			  
			  
			  humanNumberStatisticBtn.addActionListener(new ActionListener() {
			  
			  @Override public void actionPerformed(ActionEvent e) {
			  SwingUtilities.invokeLater(new Runnable() { 
				  public void run() 
				  { 
					   dispose();			           
			           HumanNumberStatistic.main(null);
			           } 
				  });
			  }
			  });
			  vtoBtn.addActionListener(new ActionListener() {
				  @Override
				  public void actionPerformed(ActionEvent e) {
					  SwingUtilities.invokeLater(new Runnable() {
						  @Override
						  public void run() {
							  dispose();
							  VTODemo.main(null);
						  }
					  });
				  }
			  });
			 

		}

		/*
		 * 功能列表组件
		 */
		private JButton faceRecognitionBtn;
		private JButton capturePictureBtn;
		private JButton realPlayBtn;
		private JButton downloadBtn;
		private JButton itsEventBtn;
		private JButton talkBtn;
		private JButton deviceSearchAndInitBtn;
		private JButton ptzBtn;
		private JButton deviceCtlBtn;
		private JButton alarmListenBtn;
		private JButton autoRegisterBtn;
		private JButton attendanceBtn;
		private JButton gateBtn;
		private JButton thermalCameraBtn;
		private JButton matrixScreenBtn;
		private JButton humanNumberStatisticBtn;
		private JButton vtoBtn;
	}
}
