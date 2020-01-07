package br.com.soluevo.microapplibrary.application.commom.utils

import androidx.fragment.app.Fragment


open class FragmentBase : Fragment() {


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        setUpBehavorBottomNavigation()
//    }
//
//    /*private fun setUpBehavorBottomNavigation() {
//        val bottomNavigationView = activity?.bottomNavigation
//        val layoutParams = bottomNavigationView?.layoutParams as CoordinatorLayout.LayoutParams
//        layoutParams.behavior = BottomNavigationBehavior()
//    }*/
//
//    fun showAlertError(message: String) {
//        val builder = AlertDialog.Builder(context!!)
//
//        builder
//            .setMessage(message.toInt())
//            .setCancelable(false)
//            .setPositiveButton("OK") { _, _ -> }
//
//        val alert = builder.create()
//        alert.show()
//    }
//
//   /* fun showConfirmDialog(title: String, message: String, dialogListener: ListenerConfirmDialog) {
//        val builder = AlertDialog.Builder(context!!)
//
//        builder
//            .setMessage(message.toInt())
//            .setCancelable(false)
//            .setPositiveButton("OK") { dialog, id ->
//                dialogListener.onPressPositiveButton(dialog, id)
//            }
//        builder.setNegativeButton(R.string.close) { dialog, id ->
//            dialogListener.onPressNegativeButton(dialog, id)
//        }
//
//        val alert = builder.create()
//        alert.show()
//    }*/
//
//    /*fun hideBottomNavigation() {
//        activity?.bottomNavigation?.visibility = View.GONE
//    }*/
//
//   /* fun showBottomNavigation() {
//        activity?.bottomNavigation?.visibility = View.VISIBLE
//    }*/
//
//    fun hideToolbar() {
//        activity?.toolbar?.visibility = View.GONE
//    }
//
//
//    fun showToolbarWithDisplayArrowBack(title: String) {
//        val toolbar = activity?.toolbar
//        toolbar?.visibility = View.VISIBLE
//        toolbar?.title = title
//
//        val appCompatActivity = activity as AppCompatActivity?
//
//        appCompatActivity?.setSupportActionBar(toolbar)
//        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
//        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    fun showToolbarWithoutDisplayArrowBack(title: String) {
//        val toolbar = activity?.toolbar
//        toolbar?.visibility = View.VISIBLE
//        toolbar?.title = title
//
//        val appCompatActivity = activity as AppCompatActivity?
//
//        appCompatActivity?.setSupportActionBar(toolbar)
//        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
//        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
//    }
//
//    fun showToast(msg: String) {
//        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
//    }
//
//    fun setUpGoogleAuth(googleAuthHandler: GoogleAuthHandler) {
//        this.googleAuthHandler = googleAuthHandler
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(this.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .requestScopes(
//                Scope("profile")
//            )
//            .build()
//
//        mGoogleSignInClient = GoogleSignIn.getClient(context!!, gso)
//    }
//
//    fun openSignIntent() {
//        val signInIntent = mGoogleSignInClient?.signInIntent
//        startActivityForResult(signInIntent, SignInActivity.GOOGLE_AUTH_REQUEST_CODE)
//    }
//
//    fun signOutGoogle() {
//        mGoogleSignInClient?.signOut()
//    }
//
//    fun signOutFireBase() {
//        auth.signOut()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == SignInActivity.GOOGLE_AUTH_REQUEST_CODE) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                account?.let {
//                    firebaseAuthWithGoogle(it)
//                }
//
//            } catch (e: ApiException) {
//                googleAuthHandler?.onApiException(e)
//                Log.w(ContentValues.TAG, "Google sign in failed", e)
//            }
//        }
//    }
//
//
//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//
//        auth.signInWithCredential(credential).addOnCompleteListener {
//            if (it.isSuccessful) {
//                googleAuthHandler?.onSuccess(acct)
//                return@addOnCompleteListener
//            }
//
//            googleAuthHandler?.onException(it.exception)
//            Log.w(ContentValues.TAG, "signInWithCredential:failure", it.exception)
//        }
//    }


}