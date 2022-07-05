using System;
using UnityEngine;
using UnityEngine.UI;

public class SimpleServices : MonoBehaviour
{
    private AndroidJavaClass UnityClass;
    private AndroidJavaObject UnityActivity;
    private AndroidJavaClass BGServicesInstance;

    private const string BGPackageName = "com.yayapipistudio.bgservice.BGServiceInstance";

    public Button StartBtn;
    public Button StopBtn;
    public Text LogText;

    private void Start()
    {
        InitializeServicesInstance(BGPackageName);
        StartBtn.onClick.AddListener(StartService);
        StopBtn.onClick.AddListener(StopService);
    }


    private void InitializeServicesInstance(string packageName)
    {
        LogText.text = "Initialize";
        try
        {
            UnityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            UnityActivity = UnityClass.GetStatic<AndroidJavaObject>("currentActivity");
            BGServicesInstance = new AndroidJavaClass(packageName);
            if (BGServicesInstance == null)
            {
                LogText.text = "BGService Null";
            }
            BGServicesInstance.CallStatic("ReceiveUnityActivity", UnityActivity);
            LogText.text = "Initialize Done";
        }
        catch(Exception e)
        {
            LogText.text = e.ToString();
        }
    }

    private void DisplayToast()
    {
        LogText.text = "Entering Toast";
        
        if (BGServicesInstance == null)
        {
            LogText.text = "BGService Null";
            return;
        }

        try
        {
            BGServicesInstance.Call("Toast", "Toast From Unity");
        }
        catch (Exception e)
        {
            LogText.text = e.ToString();
        }
    }

    private void StartService()
    {
        LogText.text = "Entering Start Services";
        
        if (BGServicesInstance == null)
        {
            LogText.text = "BGService Null";
            return;
        }

        try
        {
            BGServicesInstance.CallStatic("StartService");
        }
        catch (Exception e)
        {
            LogText.text = e.ToString();
        }
        
        LogText.text = "Exit Start Services";

    }
    
    private void StopService()
    {
        LogText.text = "Entering Start Services";
        
        if (BGServicesInstance == null)
        {
            LogText.text = "BGService Null";
            return;
        }

        try
        {
            BGServicesInstance.CallStatic("StopService");
        }
        catch (Exception e)
        {
            LogText.text = e.ToString();
        }
    }
    
}
