"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function RegisterPage() {
  const router = useRouter();

  const [form, setForm] = useState({
    userName: "",
    password: "",
    confirmPassword: "",
    role: "user",
    firstName: "",
    lastName: "",
    email: "",
    contactNo: "",
    emergencyContactNo: "",
    birthDate: "",
    passportId: "",
    permanentAddress: "",
  });

  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [formErrors, setFormErrors] = useState({
    userName: "",
    confirmPassword: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormErrors({ userName: "", confirmPassword: "" }); // Reset errors
    setMsg("");
    setSuccess(false);

    if (form.password !== form.confirmPassword) {
      setFormErrors((prev) => ({ ...prev, confirmPassword: "Passwords do not match!" }));
      return;
    }

    if (!form.userName || form.userName.length < 3) {
      setFormErrors((prev) => ({ ...prev, userName: "Username must be at least 3 characters." }));
      return;
    }
    
    if (!form.userName || !form.password || !form.firstName || !form.email) {
      setMsg("Please fill in all required fields.");
      setSuccess(false);
      return;
    }

    setLoading(true);

    try {
      const { confirmPassword, ...submitData } = form;

      const response = await fetch("http://localhost:8001/user/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(submitData),
      });

      const result = await response.text();

      if (response.ok) {
        setSuccess(true);
        setMsg("Account created successfully! Redirecting to login..."); 

        setTimeout(() => {
          router.push("/login");
        }, 1500);

      } else {
        setSuccess(false);
        setMsg(result || "Signup failed. Please check your details.");
        setLoading(false);
      }
    } catch (err) {
      setSuccess(false);
      setMsg("Connection Error! Please make sure your Spring Boot backend is running.");
      setLoading(false);
    } 

  };

  return (
    <div className="min-h-screen bg-white flex items-center justify-center p-4 md:p-6 text-gray-800">
      <div className="card w-full max-w-4xl bg-white shadow-[0_20px_50px_rgba(8,_112,_184,_0.1)] border border-gray-100 overflow-hidden">
        <div className="flex flex-col md:flex-row">
          
          {/* Left Side: Branding */}
          <div className="md:w-1/3 bg-primary p-8 text-white flex flex-col justify-center items-center text-center space-y-4">
            <div className="w-16 h-16 bg-white/20 rounded-2xl flex items-center justify-center text-3xl font-bold">SH</div>
            <h2 className="text-2xl font-bold tracking-tight">Smart Hostel</h2>
            <p className="text-primary-content/80 text-sm">Create your account to manage your stay and services.</p>
          </div>

          {/* Right Side: Form */}
          <div className="md:w-2/3 p-6 md:p-8">
            <form onSubmit={handleSubmit} className="space-y-5">
              
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">First Name</label>
                  <input type="text" placeholder="John" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.firstName} onChange={(e) => setForm({ ...form, firstName: e.target.value })} disabled={success} />
                </div>
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Last Name</label>
                  <input type="text" placeholder="Doe" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.lastName} onChange={(e) => setForm({ ...form, lastName: e.target.value })} disabled={success} />
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control">
                  <label className="label-text font-semibold mb-1 text-primary">Username (3-7 chars)</label>
                  <input type="text" placeholder="jdoe123" className={`input input-bordered w-full focus:input-primary bg-white text-gray-800 ${formErrors.userName ? 'input-error' : ''}`} value={form.userName} onChange={(e) => setForm({ ...form, userName: e.target.value })} disabled={success} />
                  {formErrors.userName && <span className="text-error text-xs mt-1">{formErrors.userName}</span>}
                </div>
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Email Address</label>
                  <input type="email" placeholder="john@example.com" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} disabled={success} />
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Password</label>
                  <input type="password" placeholder="••••••••" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} disabled={success} />
                </div>
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Confirm Password</label>
                  <input type="password" placeholder="••••••••" className={`input input-bordered w-full focus:input-primary bg-white text-gray-800 ${formErrors.confirmPassword ? 'input-error' : ''}`} value={form.confirmPassword} onChange={(e) => setForm({ ...form, confirmPassword: e.target.value })} disabled={success} />
                  {formErrors.confirmPassword && <span className="text-error text-xs mt-1">{formErrors.confirmPassword}</span>}
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Contact Number</label>
                  <input type="text" placeholder="017xxxxxxxx" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.contactNo} onChange={(e) => setForm({ ...form, contactNo: e.target.value })} disabled={success} />
                </div>
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Emergency Contact</label>
                  <input type="text" placeholder="019xxxxxxxx" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.emergencyContactNo} onChange={(e) => setForm({ ...form, emergencyContactNo: e.target.value })} disabled={success} />
                </div>
              </div>

              {/* Birth Date & Passport ID Row */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control">
                  <label className="label-text font-semibold mb-1">Birth Date</label>
                  <input type="date" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.birthDate} onChange={(e) => setForm({ ...form, birthDate: e.target.value })} disabled={success} />
                </div>
                <div className="form-control">
                  <label className="label-text font-semibold mb-1 text-slate-500">Passport ID (Optional)</label>
                  <input type="text" placeholder="E12345678" className="input input-bordered w-full focus:input-primary bg-white text-gray-800" value={form.passportId} onChange={(e) => setForm({ ...form, passportId: e.target.value })} disabled={success} />
                </div>
              </div>

              {/* Role */}
              <div className="form-control">
                <label className="label-text font-semibold mb-1">Your Role</label>
                <select className="select select-bordered w-full focus:select-primary bg-white text-gray-800" value={form.role} onChange={(e) => setForm({ ...form, role: e.target.value })} disabled={success}>
                  <option value="user">User / Student</option>
                  <option value="admin">Administrator</option>
                </select>
              </div>

              {/* Permanent Address */}
              <div className="form-control">
                <label className="label-text font-semibold mb-1">Permanent Address</label>
                <textarea placeholder="Enter full address" className="textarea textarea-bordered h-20 bg-white focus:textarea-primary text-gray-800 resize-none" value={form.permanentAddress} onChange={(e) => setForm({ ...form, permanentAddress: e.target.value })} disabled={success} />
              </div>

              {msg && (
                <div className={`alert ${success ? "alert-success" : "alert-error"} text-white transition-all py-2`}>
                  <span>{msg}</span>
                </div>
              )}

              <button type="submit" className={`btn btn-primary w-full text-white font-bold h-12 ${loading ? "loading" : ""}`} disabled={loading || success}>
                {loading ? "Registering..." : success ? "Redirecting..." : "Complete Registration"}
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}