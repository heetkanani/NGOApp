package patwa.aman.com.codeshashtra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GovernmentProgFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<schemeData> schemeDataArrayList = new ArrayList<>();

    public GovernmentProgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        schemeDataArrayList=populateList();
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_government_prog,container,false);


        recyclerView=(RecyclerView)view.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);
        if(schemeDataArrayList != null)
        {
            Log.d("TAG", "onCreate: non null list");
            RecyclerViewAdapter adaptor=new RecyclerViewAdapter(getActivity(),schemeDataArrayList);
            recyclerView.setAdapter(adaptor);
        }
        return view;

    }

    ArrayList<schemeData> populateList()
    {
        ArrayList<schemeData> schemeDataList = new ArrayList<>();
        schemeData sd = new schemeData();
        sd.title = "Beti Bachao Yojana";
        sd.description = "Beti Bachao, Beti Padhao (translation: Save girl child, educate a girl child) is a personal campaign of the Government " +
                "of India that aims to generate awareness and improve the efficiency of welfare services intended for girls. The scheme was launched " +
                "with an initial funding of â‚¹100 crore (US$15 million). It mainly targets the clusters in Uttar Pradesh, Haryana, Uttarakhand, Punjab," +
                " Bihar and Delhi. According to census data, the child gender ratio (0â€“6 years) in India was 927 girls per 1,000 boys in 2001, " +
                "which dropped to 918 girls for every 1,000 boys in 2011. A 2012 UNICEF report ranked India 41st among 195 countries. ";
        sd.incomelimit="N/A";
        sd.applicable="Girls";
        sd.nationality="Indian";

        sd.status="Active";
        sd.documents="Data Not Available";
        sd.url="http://wcd.nic.in/bbbp-schemes";
        sd.img = R.drawable.beti;
        schemeDataList.add(sd);

        schemeData sd2 = new schemeData();
        sd2.title = "One Stop Center Scheme";
        sd2.description = "It was implemented on 1st April 2015 with the 'Nirbhaya' fund. The One Stop Centres are established at various" +
                " locations in India for providing shelter, police desk, legal, " +
                "medical and counselling services to victims of violence under one roof integrated with a 24 hour Helpline. The toll free helpline number is 181.";
        sd2.incomelimit="4";
        sd2.applicable="All";
        sd2.nationality="Indian";

        sd2.status="Active";
        sd2.documents="â€¢Aadhar would be the primary KYC for the bank account.\n Recently taken passport size photograph\n";
        sd2.url="http://wcd.nic.in/schemes/one-stop-centre-scheme-1";
        sd2.img = R.drawable.step;
        schemeDataList.add(sd2);


        schemeData sd3 = new schemeData();
        sd3.title = "Working Women Hostels";
        sd3.description = "The objective of the scheme is to promote availability of safe and conveniently located accommodation " +
                "for working women, with day care facility for their children, wherever possible, in urban, semi urban, " +
                "or even rural areas where employment opportunity for women exist." ;
        sd3.incomelimit="N/A";
        sd3.applicable="Poor Youths";
        sd3.nationality="Indian";

        sd3.status="Active";
        sd3.documents="1.Annual report for last 3 years\n"+"2.Aadhar Card\n"+"3.Passport";
        sd3.url="http://wcd.nic.in/schemes/working-women-hostel";
        sd3.img = R.drawable.workingwomenhostel;
        schemeDataList.add(sd3);

        schemeData sd4 = new schemeData();
        sd4.title = "Swadhar Greh";
        sd4.description = "The Swadhar scheme was launched by the Union Ministry of Women and Child Development in 2002 for rehabilitation " +
                "of women in difficult circumstances. The scheme provides shelter, food, clothing and care to the marginalized women/girls who are " +
                "in need. The beneficiaries include widows deserted by their families and relatives, women prisoners released from jail and without " +
                "family support, women survivors of natural disasters, women victims of terrorist/extremist violence etc. The implementing agencies are mainly NGOs. ";
        sd4.incomelimit="N/A";
        sd4.applicable="Farmers";
        sd4.nationality="Indian";

        sd4.status="Active";
        sd4.documents="1.Voter ID\n"+ "2.Address Proof\n"+"3.Aadhar Card.";
        sd4.img = R.drawable.swadhar;
        schemeDataList.add(sd4);

        schemeData sd5 = new schemeData();
        sd5.title = "STEP";
        sd5.description = "The Support to Training and Employment Programme for Women (STEP) Scheme aims to provide skills that give" +
                " employability to women and to provide competencies and skill that enable women to become self-employed/ entrepreneurs. " +
                "Sectors include Agriculture, Horticulture, Food Processing, " +
                "Handlooms, Tailoring, Stitching, Embroidery, Zari etc, Handicrafts, Computer & IT enable services along " +
                "with soft skills and skills for the workplace such as spoken English, Gems & Jewellery, Travel & Tourism, Hospitality, etc.";
        sd5.incomelimit="5 ";
        sd5.applicable="All";
        sd5.nationality="Indian";

        sd5.status="Active";
        sd5.documents="1.Aadhar Card\n"+"2.Pan Card\n"+"3.Bank Passbook";
        sd5.url="http://wcd.nic.in/schemes/support-training-and-employment-programme-women-step";
        sd5.img = R.drawable.step;
        schemeDataList.add(sd5);

        schemeData sd6 = new schemeData();
        sd6.title = "Nari Shakti Puruskars ";
        sd6.description = "The Nari Shakti Puruskars are national level awards recognizing the efforts made by women and institutions in rendering " +
                "distinguished services for the cause of women, especially vulnerable and marginalized women.20 Nari Shakti awards will be conferred each year.\n" +
                "The recipients of the Puruskars would be declared every year on 20th February and awards " +
                "will be conferred on 8th March on the occasion of International Womenâ€™s Day (IWD).\n";
        sd6.incomelimit="N/A";
        sd6.applicable="Small Scale Farmers";
        sd6.nationality="Indian";

        sd6.status="Active";
        sd6.documents="Data Not Available";
        sd6.url="http://wcd.nic.in/schemes/nari-shakti-puraskar";
        sd6.img = R.drawable.nari;
        schemeDataList.add(sd6);

        schemeData sd7 = new schemeData();
        sd7.title = "Mahila E-Haat";
        sd7.description = "It is a direct online marketing platform launched by the Ministry of Women and Child Development to support women " +
                "entrepreneurs, Self Helf Groups (SHGs) and Non- Governmental Organisations (NGOs) to showcase products made and services rendered" +
                " by them. This is a part of the 'Digital India' initiative.";
        sd7.incomelimit="N/A";
        sd7.applicable="Non Working Women";
        sd7.nationality="Indian";

        sd7.status="Active";
        sd7.documents="Aadhaar Card";
        sd7.url="http://mahilaehaat-rmk.gov.in/en/";
        sd7.img = R.drawable.mahilaehatt;
        schemeDataList.add(sd7);


        schemeData sd8 = new schemeData();
        sd8.title = "Scholarship";
        sd8.description = "A scholarship is provided to girls from rural areas whose family income is less than Rs. 10,000/- per annum, " +
                "in order to reduce the drop out rate at the primary and secondary school levels. An amount of Rs. 25/- per month is " +
                "given to girls studying in in V, VI and VII standards, and Rs. 50/- per month to girls studying in VII, IX and X standards.";
        sd8.incomelimit="N/A";
        sd8.applicable="ALL";
        sd8.nationality="Indian";

        sd8.status="Active";
        sd8.documents="1.Income Cretificate\n"+ "2.Minimum 80% attendance in previous class\n"+"3. Successful completion of examination with marksheet" ;
        sd8.url="http://www.nari.nic.in/schemes/scholarships-girls-rural-areas";
        sd8.img = R.drawable.scholarship;
        schemeDataList.add(sd8);

        return schemeDataList;



       /* schemeData sd9 = new schemeData();
        sd9.title = "Scholarship Scheme ";
        sd9.description = "The Ministry of Minority Affairs, is a ministry of the Government of India established in 2006. It is the apex body for the central governments regulatory and developmental programmes for the minority religious communities in India, which include Muslims, Sikhs, Christians, Buddhists, Zoroastrians (Parsis) and Jains notified as minority communities as notified by GOI in Gazette [2]under Section 2 (c) of the National Commission for Minorities Act, 1992.As of 13 October 2015, head of the ministry, Minister of Minority Affairs is the cabinet minister Mukhtar Abbas Naqvi. The ministry is also involved with the linguistic minorities and of the office of the Commissioner for Linguistic Minorities.";
        sd9.incomelimit="N/A";
        sd9.applicable="Minority Institutes";
        sd9.nationality="Indian";
        sd9.agegrp="N/A";
        sd9.status="Active";
        sd9.documents="Data Not Available";
        sd9.url="https://scholarships.gov.in/";
        //sd9.img = R.drawable.scholarship;
        schemeDataList.add(sd9);*/

    }

}
