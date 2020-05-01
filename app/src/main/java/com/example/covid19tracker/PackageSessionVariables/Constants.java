package com.example.covid19tracker.PackageSessionVariables;

import com.example.covid19tracker.PackageFragments.FragmentAbout;
import com.example.covid19tracker.PackageFragments.FragmentCountries;
import com.example.covid19tracker.PackageFragments.FragmentCountry;
import com.example.covid19tracker.PackageFragments.FragmentWorld;
import com.example.covid19tracker.PackageFragments.FragmentMyCountry;
import com.example.covid19tracker.PackageFragments.FragmentTimeline;
import com.example.covid19tracker.PackageFragments.FragmentDashboard;
import com.example.covid19tracker.PackageObjectModels.MoreInformationItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Constants {


    public static final String TAG_FRAGMENT_DASHBOARD = FragmentDashboard.class.getSimpleName();
    public static final String TAG_FRAGMENT_WORLD = FragmentWorld.class.getSimpleName();
    public static final String TAG_FRAGMENT_COUNTRIES = FragmentCountries.class.getSimpleName();
    public static final String TAG_FRAGMENT_MY_COUNTRY = FragmentMyCountry.class.getSimpleName();
    public static final String TAG_FRAGMENT_ABOUT = FragmentAbout.class.getSimpleName();
    public static final String TAG_FRAGMENT_COUNTRY = FragmentCountry.class.getSimpleName();
    public static final String TAG_FRAGMENT_TIMELINE = FragmentTimeline.class.getSimpleName();

    public static int SPLASH_SCREEN_DURATION = 2000;

    public static final String DASHBOARD = "Dashboard";
    public static final String WORLD = "World";
    public static final String COUNTRIES = "Countries";
    public static final String MY_COUNTRY = "My Country";
    public static final String ABOUT = "About";

    public static final String ADVICE_FOR_THE_PUBLIC = "Advice for the Public";
    public static final String MYTH_BUSTERS = "Myth Busters";
    public static final String QUESTIONS_AND_ANSWERS = "Questions and Answers";

    public static final String ACTIVE = "Active";
    public static final String DEATHS = "Deaths";
    public static final String RECOVERED = "Recovered";

    public static final DecimalFormat FORMAT_WITH_COMMA = new DecimalFormat("#,###");

    public static final String DATE_TIME_FORMAT = "MMM dd, yyyy (EEE) | hh:mm a";
    public static final String DATE_FORMAT = "MMM dd, yyyy (EEE)";
    public static final String DATE_FORMAT_FROM_API = "M/dd/yy";
    public static final String DATE_FORMAT_FROM_API_2 = "M/dd/yyyy";
    public static final String DATE_FORMAT_FROM_API_3 = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String MY_COUNTRY_NAME = "Philippines";

    public static final int ANIMATION_DURATION = 1500;


    public static final List<MoreInformationItem> ADVICE_FOR_THE_PUBLIC_LIST = new ArrayList<>(Arrays.asList(
            new MoreInformationItem(
                    "Wash your hands frequently",
                    "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.\n" +
                            "\n" +
                            "Why? Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.",
                    false
            ),
            new MoreInformationItem(
                    "Maintain social distancing",
                    "Maintain at least 1 metre (3 feet) distance between yourself and anyone who is coughing or sneezing.\n" +
                            "\n" +
                            "Why? When someone coughs or sneezes they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person coughing has the disease.",
                    false
            ),
            new MoreInformationItem(
                    "Avoid touching eyes, nose and mouth",
                    "Why? Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter your body and can make you sick.",
                    false
            ),
            new MoreInformationItem(
                    "Practice respiratory hygiene",
                    "Make sure you, and the people around you, follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. Then dispose of the used tissue immediately.\n" +
                            "\n" +
                            "Why? Droplets spread virus. By following good respiratory hygiene you protect the people around you from viruses such as cold, flu and COVID-19.",
                    false
            ),
            new MoreInformationItem(
                    "If you have fever, cough and difficulty breathing, seek medical care early",
                    "Stay home if you feel unwell. If you have a fever, cough and difficulty breathing, seek medical attention and call in advance. Follow the directions of your local health authority.\n" +
                            "\n" +
                            "Why? National and local authorities will have the most up to date information on the situation in your area. Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also protect you and help prevent spread of viruses and other infections.",
                    false
            ), new MoreInformationItem(
                    "Stay informed and follow advice given by your healthcare provider",
                    "Stay informed on the latest developments about COVID-19. Follow advice given by your healthcare provider, your national and local public health authority or your employer on how to protect yourself and others from COVID-19.\n" +
                            "\n" +
                            "Why? National and local authorities will have the most up to date information on whether COVID-19 is spreading in your area. They are best placed to advise on what people in your area should be doing to protect themselves.",
                    false
            )
    ));


    public static final List<MoreInformationItem> MYTH_BUSTER_LIST = new ArrayList<>(Arrays.asList(
            new MoreInformationItem(
                    "Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)",
                    "You can catch COVID-19, no matter how sunny or hot the weather is. Countries with hot weather have reported cases of COVID-19. To protect yourself, make sure you clean your hands frequently and thoroughly and avoid touching your eyes, mouth, and nose.",
                    false
            ),
            new MoreInformationItem(
                    "You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you will have it for life.",
                    "Most of the people who catch COVID-19 can recover and eliminate the virus from their bodies. If you catch the disease, make sure you treat your symptoms. If you have cough, fever, and difficulty breathing, seek medical care early – but call your health facility by telephone first. Most patients recover thanks to supportive care.",
                    false
            ),
            new MoreInformationItem(
                    "Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.",
                    "The most common symptoms of COVID-19 are dry cough, tiredness and fever. Some people may develop more severe forms of the disease, such as pneumonia. The best way to confirm if you have  the virus producing COVID-19 disease is with a laboratory test.  You cannot confirm it with this breathing exercise, which can even be dangerous.",
                    false
            ),
            new MoreInformationItem(
                    "Drinking alcohol does not protect you against COVID-19 and can be dangerous",
                    "Frequent or excessive alcohol consumption can increase your risk of health problems.",
                    false
            ),
            new MoreInformationItem(
                    "COVID-19 virus can be transmitted in areas with hot and humid climates",
                    "From the evidence so far, the COVID-19 virus can be transmitted in ALL AREAS, including areas with hot and humid weather. Regardless of climate, adopt protective measures if you live in, or travel to an area reporting COVID-19. The best way to protect yourself against COVID-19 is by frequently cleaning your hands. By doing this you eliminate viruses that may be on your hands and avoid infection that could occur by then touching your eyes, mouth, and nose.",
                    false
            ),
            new MoreInformationItem(
                    "Cold weather and snow CANNOT kill the new coronavirus.",
                    "There is no reason to believe that cold weather can kill the new coronavirus or other diseases. The normal human body temperature remains around 36.5°C to 37°C, regardless of the external temperature or weather. The most effective way to protect yourself against the new coronavirus is by frequently cleaning your hands with alcohol-based hand rub or washing them with soap and water.",
                    false
            ),
            new MoreInformationItem(
                    "Taking a hot bath does not prevent the new coronavirus disease",
                    "Taking a hot bath will not prevent you from catching COVID-19. Your normal body temperature remains around 36.5°C to 37°C, regardless of the temperature of your bath or shower. Actually, taking a hot bath with extremely hot water can be harmful, as it can burn you. The best way to protect yourself against COVID-19 is by frequently cleaning your hands. By doing this you eliminate viruses that may be on your hands and avoid infection that could occur by then touching your eyes, mouth, and nose.",
                    false
            ),
            new MoreInformationItem(
                    "The new coronavirus CANNOT be transmitted through mosquito bites.",
                    "To date there has been no information nor evidence to suggest that the new coronavirus could be transmitted by mosquitoes. The new coronavirus is a respiratory virus which spreads primarily through droplets generated when an infected person coughs or sneezes, or through droplets of saliva or discharge from the nose. To protect yourself, clean your hands frequently with an alcohol-based hand rub or wash them with soap and water. Also, avoid close contact with anyone who is coughing and sneezing.",
                    false
            ),
            new MoreInformationItem(
                    "Are hand dryers effective in killing the new coronavirus?",
                    "No. Hand dryers are not effective in killing the 2019-nCoV. To protect yourself against the new coronavirus, you should frequently clean your hands with an alcohol-based hand rub or wash them with soap and water. Once your hands are cleaned, you should dry them thoroughly by using paper towels or a warm air dryer.",
                    false
            ),
            new MoreInformationItem(
                    "Can an ultraviolet disinfection lamp kill the new coronavirus?",
                    "UV lamps should not be used to sterilize hands or other areas of skin as UV radiation can cause skin irritation.",
                    false
            ),
            new MoreInformationItem(
                    "How effective are thermal scanners in detecting people infected with the new coronavirus?",
                    "Thermal scanners are effective in detecting people who have developed a fever (i.e. have a higher than normal body temperature) because of infection with the new coronavirus.\n" +
                            "\n" +
                            "However, they cannot detect people who are infected but are not yet sick with fever. This is because it takes between 2 and 10 days before people who are infected become sick and develop a fever.",
                    false
            ),
            new MoreInformationItem(
                    "Can spraying alcohol or chlorine all over your body kill the new coronavirus?",
                    "No. Spraying alcohol or chlorine all over your body will not kill viruses that have already entered your body. Spraying such substances can be harmful to clothes or mucous membranes (i.e. eyes, mouth). Be aware that both alcohol and chlorine can be useful to disinfect surfaces, but they need to be used under appropriate recommendations.",
                    false
            ),
            new MoreInformationItem(
                    "Do vaccines against pneumonia protect you against the new coronavirus?",
                    "No. Vaccines against pneumonia, such as pneumococcal vaccine and Haemophilus influenza type B (Hib) vaccine, do not provide protection against the new coronavirus.\n" +
                            "\n" +
                            "The virus is so new and different that it needs its own vaccine. Researchers are trying to develop a vaccine against 2019-nCoV, and WHO is supporting their efforts.\n" +
                            "\n" +
                            "Although these vaccines are not effective against 2019-nCoV, vaccination against respiratory illnesses is highly recommended to protect your health.",
                    false
            ),
            new MoreInformationItem(
                    "Can regularly rinsing your nose with saline help prevent infection with the new coronavirus?",
                    "No. There is no evidence that regularly rinsing the nose with saline has protected people from infection with the new coronavirus. \n" +
                            "\n" +
                            "There is some limited evidence that regularly rinsing nose with saline can help people recover more quickly from the common cold. However, regularly rinsing the nose has not been shown to prevent respiratory infections.",
                    false
            ),
            new MoreInformationItem(
                    "Can eating garlic help prevent infection with the new coronavirus?",
                    "Garlic is a healthy food that may have some antimicrobial properties. However, there is no evidence from the current outbreak that eating garlic has protected people from the new coronavirus.",
                    false
            ),
            new MoreInformationItem(
                    "Does the new coronavirus affect older people, or are younger people also susceptible?",
                    "People of all ages can be infected by the new coronavirus (2019-nCoV). Older people, and people with pre-existing medical conditions (such as asthma, diabetes, heart disease) appear to be more vulnerable to becoming severely ill with the virus. \n" +
                            "\n" +
                            "WHO advises people of all ages to take steps to protect themselves from the virus, for example by following good hand hygiene and good respiratory hygiene.",
                    false
            ),
            new MoreInformationItem(
                    "Are antibiotics effective in preventing and treating the new coronavirus?",
                    "No, antibiotics do not work against viruses, only bacteria.\n" +
                            "\n" +
                            "The new coronavirus (2019-nCoV) is a virus and, therefore, antibiotics should not be used as a means of prevention or treatment.\n" +
                            "\n" +
                            "However, if you are hospitalized for the 2019-nCoV, you may receive antibiotics because bacterial co-infection is possible.",
                    false
            ),
            new MoreInformationItem(
                    "Are there any specific medicines to prevent or treat the new coronavirus?",
                    "To date, there is no specific medicine recommended to prevent or treat the new coronavirus (2019-nCoV).\n" +
                            "\n" +
                            "However, those infected with the virus should receive appropriate care to relieve and treat symptoms, and those with severe illness should receive optimized supportive care. Some specific treatments are under investigation, and will be tested through clinical trials. WHO is helping to accelerate research and development efforts with a range or partners.",
                    false
            )
    ));


    public static final List<MoreInformationItem> QUESTION_AND_ANSWER_LIST = new ArrayList<>(Arrays.asList(
            new MoreInformationItem(
                    "What is a coronavirus?",
                    "Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19.",
                    false
            ),
            new MoreInformationItem(
                    "What is COVID-19?",
                    "COVID-19 is the infectious disease caused by the most recently discovered coronavirus. This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019.",
                    false
            ),
            new MoreInformationItem(
                    "What are the symptoms of COVID-19?",
                    "The most common symptoms of COVID-19 are fever, tiredness, and dry cough. Some patients may have aches and pains, nasal congestion, runny nose, sore throat or diarrhea. These symptoms are usually mild and begin gradually. Some people become infected but don’t develop any symptoms and don't feel unwell. Most people (about 80%) recover from the disease without needing special treatment. Around 1 out of every 6 people who gets COVID-19 becomes seriously ill and develops difficulty breathing. Older people, and those with underlying medical problems like high blood pressure, heart problems or diabetes, are more likely to develop serious illness. People with fever, cough and difficulty breathing should seek medical attention.",
                    false
            ),
            new MoreInformationItem(
                    "How does COVID-19 spread?",
                    "People can catch COVID-19 from others who have the virus. The disease can spread from person to person through small droplets from the nose or mouth which are spread when a person with COVID-19 coughs or exhales. These droplets land on objects and surfaces around the person. Other people then catch COVID-19 by touching these objects or surfaces, then touching their eyes, nose or mouth. People can also catch COVID-19 if they breathe in droplets from a person with COVID-19 who coughs out or exhales droplets. This is why it is important to stay more than 1 meter (3 feet) away from a person who is sick.\n" +
                            "\n" +
                            "WHO is assessing ongoing research on the ways COVID-19 is spread and will continue to share updated findings.",
                    false
            ),
            new MoreInformationItem(
                    "Can the virus that causes COVID-19 be transmitted through the air?",
                    "Studies to date suggest that the virus that causes COVID-19 is mainly transmitted through contact with respiratory droplets rather than through the air.  See previous answer on “How does COVID-19 spread?”",
                    false
            ),
            new MoreInformationItem(
                    "Can CoVID-19 be caught from a person who has no symptoms?",
                    "The main way the disease spreads is through respiratory droplets expelled by someone who is coughing. The risk of catching COVID-19 from someone with no symptoms at all is very low. However, many people with COVID-19 experience only mild symptoms. This is particularly true at the early stages of the disease. It is therefore possible to catch COVID-19 from someone who has, for example, just a mild cough and does not feel ill.  WHO is assessing ongoing research on the period of transmission of COVID-19 and will continue to share updated findings.",
                    false
            ),
            new MoreInformationItem(
                    "Can I catch COVID-19 from the feces of someone with the disease?",
                    "The risk of catching COVID-19 from the feces of an infected person appears to be low. While initial investigations suggest the virus may be present in feces in some cases, spread through this route is not a main feature of the outbreak. WHO is assessing ongoing research on the ways COVID-19 is spread and will continue to share new findings. Because this is a risk, however, it is another reason to clean hands regularly, after using the bathroom and before eating.",
                    false
            ),
            new MoreInformationItem(
                    "What can I do to protect myself and prevent the spread of disease?",
                    "Stay aware of the latest information on the COVID-19 outbreak, available on the WHO website and through your national and local public health authority. Many countries around the world have seen cases of COVID-19 and several have seen outbreaks. Authorities in China and some other countries have succeeded in slowing or stopping their outbreaks. However, the situation is unpredictable so check regularly for the latest news.\n" +
                            "\n" +
                            "You can reduce your chances of being infected or spreading COVID-19 by taking some simple precautions:\n" +
                            "\n" +
                            "- Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.\n" +
                            "Why? Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.\n" +
                            "- Maintain at least 1 metre (3 feet) distance between yourself and anyone who is coughing or sneezing.\n" +
                            "Why? When someone coughs or sneezes they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person coughing has the disease.\n" +
                            "- Avoid touching eyes, nose and mouth.\n" +
                            "Why? Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter your body and can make you sick.\n" +
                            "- Make sure you, and the people around you, follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. Then dispose of the used tissue immediately.\n" +
                            "Why? Droplets spread virus. By following good respiratory hygiene you protect the people around you from viruses such as cold, flu and COVID-19.\n" +
                            "- Stay home if you feel unwell. If you have a fever, cough and difficulty breathing, seek medical attention and call in advance. Follow the directions of your local health authority.\n" +
                            "Why? National and local authorities will have the most up to date information on the situation in your area. Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also protect you and help prevent spread of viruses and other infections.\n" +
                            "- Keep up to date on the latest COVID-19 hotspots (cities or local areas where COVID-19 is spreading widely). If possible, avoid traveling to places  – especially if you are an older person or have diabetes, heart or lung disease.\n" +
                            "Why? You have a higher chance of catching COVID-19 in one of these areas.",
                    false
            ),
            new MoreInformationItem(
                    "Protection measures for persons who are in or have recently visited (past 14 days) areas where COVID-19 is spreading",
                    "- Follow the guidance outlined above (Protection measures for everyone)\n" +
                            "- Self-isolate by staying at home if you begin to feel unwell, even with mild symptoms such as headache, low grade fever (37.3 C or above) and slight runny nose, until you recover. If it is essential for you to have someone bring you supplies or to go out, e.g. to buy food, then wear a mask to avoid infecting other people.\n" +
                            "Why? Avoiding contact with others and visits to medical facilities will allow these facilities to operate more effectively and help protect you and others from possible COVID-19 and other viruses.\n" +
                            "- If you develop fever, cough and difficulty breathing, seek medical advice promptly as this may be due to a respiratory infection or other serious condition. Call in advance and tell your provider of any recent travel or contact with travelers.\n" +
                            "Why? Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also help to prevent possible spread of COVID-19 and other viruses.",
                    false
            ),
            new MoreInformationItem(
                    "How likely am I to catch COVID-19?",
                    "The risk depends on where you  are - and more specifically, whether there is a COVID-19 outbreak unfolding there.\n" +
                            "\n" +
                            "For most people in most locations the risk of catching COVID-19 is still low. However, there are now places around the world (cities or areas) where the disease is spreading. For people living in, or visiting, these areas the risk of catching COVID-19 is higher. Governments and health authorities are taking vigorous action every time a new case of COVID-19 is identified. Be sure to comply with any local restrictions on travel, movement or large gatherings. Cooperating with disease control efforts will reduce your risk of catching or spreading COVID-19.\n" +
                            "\n" +
                            "COVID-19 outbreaks can be contained and transmission stopped, as has been shown in China and some other countries. Unfortunately, new outbreaks can emerge rapidly. It’s important to be aware of the situation where you are or intend to go. WHO publishes daily updates on the COVID-19 situation worldwide.",
                    false
            ),
            new MoreInformationItem(
                    "Should I worry about COVID-19?",
                    "Illness due to COVID-19 infection is generally mild, especially for children and young adults. However, it can cause serious illness: about 1 in every 5 people who catch it need hospital care. It is therefore quite normal for people to worry about how the COVID-19 outbreak will affect them and their loved ones.\n" +
                            "\n" +
                            "We can channel our concerns into actions to protect ourselves, our loved ones and our communities. First and foremost among these actions is regular and thorough hand-washing and good respiratory hygiene. Secondly, keep informed and follow the advice of the local health authorities including any restrictions put in place on travel, movement and gatherings.",
                    false
            ),
            new MoreInformationItem(
                    "Who is at risk of developing severe illness?",
                    "While we are still learning about how COVID-2019 affects people, older persons and persons with pre-existing medical conditions (such as high blood pressure, heart disease, lung disease, cancer or diabetes)  appear to develop serious illness more often than others.",
                    false
            ),
            new MoreInformationItem(
                    "Are antibiotics effective in preventing or treating the COVID-19?",
                    "No. Antibiotics do not work against viruses, they only work on bacterial infections. COVID-19 is caused by a virus, so antibiotics do not work. Antibiotics should not be used as a means of prevention or treatment of COVID-19. They should only be used as directed by a physician to treat a bacterial infection.",
                    false
            ),
            new MoreInformationItem(
                    "Are there any medicines or therapies that can prevent or cure COVID-19?",
                    "While some western, traditional or home remedies may provide comfort and alleviate symptoms of COVID-19, there is no evidence that current medicine can prevent or cure the disease. WHO does not recommend self-medication with any medicines, including antibiotics, as a prevention or cure for COVID-19. However, there are several ongoing clinical trials that include both western and traditional medicines. WHO will continue to provide updated information as soon as clinical findings are available.",
                    false
            ),
            new MoreInformationItem(
                    "Is there a vaccine, drug or treatment for COVID-19?",
                    "Not yet. To date, there is no vaccine and no specific antiviral medicine to prevent or treat COVID-2019. However, those affected should receive care to relieve symptoms. People with serious illness should be hospitalized. Most patients recover thanks to supportive care.\n" +
                            "\n" +
                            "Possible vaccines and some specific drug treatments are under investigation. They are being tested through clinical trials. WHO is coordinating efforts to develop vaccines and medicines to prevent and treat COVID-19.\n" +
                            "\n" +
                            "The most effective ways to protect yourself and others against COVID-19 are to frequently clean your hands, cover your cough with the bend of elbow or tissue, and maintain a distance of at least 1 meter (3 feet) from people who are coughing or sneezing.",
                    false
            ),
            new MoreInformationItem(
                    "Is COVID-19 the same as SARS?",
                    "No. The virus that causes COVID-19 and the one that caused the outbreak of Severe Acute Respiratory Syndrome (SARS) in 2003 are related to each other genetically, but the diseases they cause are quite different.\n" +
                            "\n" +
                            "SARS was more deadly but much less infectious than COVID-19. There have been no outbreaks of SARS anywhere in the world since 2003.",
                    false
            ),
            new MoreInformationItem(
                    "Should I wear a mask to protect myself?",
                    "Only wear a mask if you are ill with COVID-19 symptoms (especially coughing) or looking after someone who may have COVID-19. Disposable face mask can only be used once. If you are not ill or looking after someone who is ill then you are wasting a mask. There is a world-wide shortage of masks, so WHO urges people to use masks wisely.\n" +
                            "\n" +
                            "WHO advises rational use of medical masks to avoid unnecessary wastage of precious resources and mis-use of masks.\n" +
                            "\n" +
                            "The most effective ways to protect yourself and others against COVID-19 are to frequently clean your hands, cover your cough with the bend of elbow or tissue and maintain a distance of at least 1 meter (3 feet) from people who are coughing or sneezing.",
                    false
            ),
            new MoreInformationItem(
                    "How to put on, use, take off and dispose of a mask?",
                    "1. Remember, a mask should only be used by health workers, care takers, and individuals with respiratory symptoms, such as fever and cough.\n" +
                            "2. Before touching the mask, clean hands with an alcohol-based hand rub or soap and water\n" +
                            "3. Take the mask and inspect it for tears or holes.\n" +
                            "4. Orient which side is the top side (where the metal strip is).\n" +
                            "5. Ensure the proper side of the mask faces outwards (the coloured side).\n" +
                            "6. Place the mask to your face. Pinch the metal strip or stiff edge of the mask so it moulds to the shape of your nose.\n" +
                            "7. Pull down the mask’s bottom so it covers your mouth and your chin.\n" +
                            "8. After use, take off the mask; remove the elastic loops from behind the ears while keeping the mask away from your face and clothes, to avoid touching potentially contaminated surfaces of the mask.\n" +
                            "9. Discard the mask in a closed bin immediately after use.\n" +
                            "10. Perform hand hygiene after touching or discarding the mask – Use alcohol-based hand rub or, if visibly soiled, wash your hands with soap and water.",
                    false
            ),
            new MoreInformationItem(
                    "How long is the incubation period for COVID-19?",
                    "The “incubation period” means the time between catching the virus and beginning to have symptoms of the disease. Most estimates of the incubation period for COVID-19 range from 1-14 days, most commonly around five days. These estimates will be updated as more data become available.",
                    false
            ),
            new MoreInformationItem(
                    "Can humans become infected with the COVID-19 from an animal source?",
                    "Coronaviruses are a large family of viruses that are common in animals. Occasionally, people get infected with these viruses which may then spread to other people. For example, SARS-CoV was associated with civet cats and MERS-CoV is transmitted by dromedary camels. Possible animal sources of COVID-19 have not yet been confirmed.  \n" +
                            "\n" +
                            "To protect yourself, such as when visiting live animal markets, avoid direct contact with animals and surfaces in contact with animals. Ensure good food safety practices at all times. Handle raw meat, milk or animal organs with care to avoid contamination of uncooked foods and avoid consuming raw or undercooked animal products.",
                    false
            ),
            new MoreInformationItem(
                    "Can I catch COVID-19 from my pet?",
                    "While there has been one instance of a dog being infected in Hong Kong, to date, there is no evidence that a dog, cat or any pet can transmit COVID-19. COVID-19 is mainly spread through droplets produced when an infected person coughs, sneezes, or speaks. To protect yourself, clean your hands frequently and thoroughly. \n" +
                            "\n" +
                            "WHO continues to monitor the latest research on this and other COVID-19 topics and will update as new findings are available.",
                    false
            ),
            new MoreInformationItem(
                    "How long does the virus survive on surfaces?",
                    "It is not certain how long the virus that causes COVID-19 survives on surfaces, but it seems to behave like other coronaviruses. Studies suggest that coronaviruses (including preliminary information on the COVID-19 virus) may persist on surfaces for a few hours or up to several days. This may vary under different conditions (e.g. type of surface, temperature or humidity of the environment).\n" +
                            "\n" +
                            "If you think a surface may be infected, clean it with simple disinfectant to kill the virus and protect yourself and others. Clean your hands with an alcohol-based hand rub or wash them with soap and water. Avoid touching your eyes, mouth, or nose.",
                    false
            ),
            new MoreInformationItem(
                    "Is it safe to receive a package from any area where COVID-19 has been reported?",
                    "Yes. The likelihood of an infected person contaminating commercial goods is low and the risk of catching the virus that causes COVID-19 from a package that has been moved, travelled, and exposed to different conditions and temperature is also low.",
                    false
            ),
            new MoreInformationItem(
                    "Is there anything I should not do?",
                    "The following measures ARE NOT effective against COVID-2019 and can be harmful:\n" +
                            "\n" +
                            "- Smoking\n" +
                            "- Wearing multiple masks\n" +
                            "- Taking antibiotics\n" +
                            "In any case, if you have fever, cough and difficulty breathing seek medical care early to reduce the risk of developing a more severe infection and be sure to share your recent travel history with your health care provider.",
                    false
            ),
            new MoreInformationItem(
                    "Is the source of the coronavirus causing COVID-19 known?",
                    "Currently, the source of SARS-CoV-2, the coronavirus (CoV) causing COVID-19 is unknown. All available evidence suggests that SARS-CoV-2 has a natural animal origin and is not a constructed virus. SARS-CoV-2 virus most probably has its ecological reservoir in bats. SARS-CoV-2, belongs to a group of genetically related viruses, which also include SARS-CoV and a number of other CoVs isolated from bats populations. MERS-CoV also belongs to this group, but is less closely related.",
                    false
            ),
            new MoreInformationItem(
                    "How did the first human SARS-CoV-2 infections occur?",
                    "The first human cases of COVID-19 were identified in Wuhan City, China in December 2019. At this stage, it is not possible to determine precisely how humans in China were initially infected with SARS-CoV-2.\n" +
                            "\n" +
                            "However, SARS-CoV, the virus which caused the SARS outbreak in 2003, jumped from an animal reservoir (civet cats, a farmed wild animal) to humans and then spread between humans. In a similar way, it is thought that SARS-CoV-2 jumped the species barrier and initially infected humans, but more likely through an intermediate host, that is another animal species more likely to be handled by humans - this could be a domestic animal, a wild animal, or a domesticated wild animal and, as of yet, has not been identified.\n" +
                            "\n" +
                            "Until the source of this virus is identified and controlled, there is a risk of reintroduction of the virus in the human population and the risk of new outbreaks like the ones we are currently experiencing.",
                    false
            ),
            new MoreInformationItem(
                    "Is COVID-19 airborne?",
                    "The virus that causes COVID-19 is mainly transmitted through droplets generated when an infected person coughs, sneezes, or speaks. These droplets are too heavy to hang in the air. They quickly fall on floors or surfaces. \n" +
                            "\n" +
                            "You can be infected by breathing in the virus if you are within 1 metre of a person who has COVID-19, or by touching a contaminated surface and then touching your eyes, nose or mouth before washing your hands.",
                    false
            )
    ));


}
